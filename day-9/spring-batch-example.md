# Bulk Import Assets with Spring Batch

## 1. Project Structure 

Add a **batch module inside your app**, don’t mix it with REST code.

```text
com.greenko.asset
 ├── controller
 ├── service
 ├── repository
 ├── entity
 ├── batch
 │    ├── config
 │    ├── job
 │    ├── step
 │    ├── reader
 │    ├── processor
 │    └── writer
```

---

## 2. CSV File Format

`assets.csv`

```csv
assetId,assetName,location,capacity,status
A101,Solar Plant 1,Bangalore,50,ACTIVE
A102,Wind Farm 1,Chennai,120,ACTIVE
A103,Hydro Plant 1,Pune,200,INACTIVE
```

---


## 3. CSV Reader (ItemReader)

Reads **one row at a time**, not the whole file.

```java
@Bean
public FlatFileItemReader<Asset> assetReader() {
    FlatFileItemReader<Asset> reader = new FlatFileItemReader<>();
    reader.setResource(new FileSystemResource("input/assets.csv"));
    reader.setLinesToSkip(1);

    DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
    tokenizer.setNames("assetId", "assetName", "location", "capacity", "status");

    BeanWrapperFieldSetMapper<Asset> mapper =
            new BeanWrapperFieldSetMapper<>();
    mapper.setTargetType(Asset.class);

    DefaultLineMapper<Asset> lineMapper = new DefaultLineMapper<>();
    lineMapper.setLineTokenizer(tokenizer);
    lineMapper.setFieldSetMapper(mapper);

    reader.setLineMapper(lineMapper);
    return reader;
}
```

> “One CSV row → One Asset object”

---

## 4. Processor (Validation / Cleanup)

This is where **business rules** go.

```java
@Bean
public ItemProcessor<Asset, Asset> assetProcessor() {
    return asset -> {
        if (asset.getCapacity() <= 0) {
            return null; // skip invalid record
        }
        asset.setAssetName(asset.getAssetName().trim());
        return asset;
    };
}
```



---

## 5. JPA Writer

```java
@Bean
public JpaItemWriter<Asset> assetWriter(EntityManagerFactory emf) {
    JpaItemWriter<Asset> writer = new JpaItemWriter<>();
    writer.setEntityManagerFactory(emf);
    return writer;
}
```

## 6. Step Configuration (Chunk Processing)

```java
@Bean
public Step assetImportStep(StepBuilderFactory stepBuilderFactory) {
    return stepBuilderFactory.get("assetImportStep")
        .<Asset, Asset>chunk(50)
        .reader(assetReader())
        .processor(assetProcessor())
        .writer(assetWriter(null))
        .build();
}
```

### What `chunk(50)` means

```text
Read 50 rows
→ Process 50
→ Save 50
→ Commit
```

✔ Low memory
✔ Fast inserts
✔ Restart safe

---

## 7. Job Configuration

```java
@Bean
public Job assetImportJob(JobBuilderFactory jobBuilderFactory) {
    return jobBuilderFactory.get("assetImportJob")
        .start(assetImportStep(null))
        .build();
}
```

---

## 8. How to Trigger the Batch Job

### Option 1: Manual Admin API 

```java
@RestController
@RequestMapping("/admin/batch")
public class BatchController {

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    Job assetImportJob;

    @PostMapping("/import-assets")
    public String runJob() throws Exception {
        JobParameters params = new JobParametersBuilder()
            .addLong("startAt", System.currentTimeMillis())
            .toJobParameters();

        jobLauncher.run(assetImportJob, params);
        return "Asset import started";
    }
}
```

✔ Non-blocking
✔ Safe
✔ Production friendly

---

### Option 2: Scheduled 

```java
@Scheduled(cron = "0 0 1 * * *")
public void importAssets() {
    jobLauncher.run(assetImportJob, new JobParameters());
}
```

---

## 9. What Happens If Job Fails?

Example:

* 10,000 assets
* Failure at record 3,200

Spring Batch will:

* Store progress in batch tables
* Restart from **3,201**
* No duplicates
* No data loss

This is the **biggest win** over REST-based imports.








