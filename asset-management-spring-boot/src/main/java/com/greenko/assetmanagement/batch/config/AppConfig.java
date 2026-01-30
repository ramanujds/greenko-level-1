//package com.greenko.assetmanagement.batch.config;
//
//import com.greenko.assetmanagement.dto.AssetRequestDto;
//import com.greenko.assetmanagement.model.Asset;
//import com.greenko.assetmanagement.model.AssetHealth;
//import com.greenko.assetmanagement.model.AssetStatus;
//import jakarta.persistence.EntityManagerFactory;
//import org.springframework.batch.core.job.Job;
//import org.springframework.batch.core.job.builder.JobBuilder;
//import org.springframework.batch.core.step.Step;
//import org.springframework.batch.core.step.builder.StepBuilder;
//import org.springframework.batch.infrastructure.item.ItemProcessor;
//import org.springframework.batch.infrastructure.item.database.JpaItemWriter;
//import org.springframework.batch.infrastructure.item.file.FlatFileItemReader;
//import org.springframework.batch.infrastructure.item.file.mapping.BeanWrapperFieldSetMapper;
//import org.springframework.batch.infrastructure.item.file.mapping.DefaultLineMapper;
//import org.springframework.batch.infrastructure.item.file.transform.DelimitedLineTokenizer;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.FileSystemResource;
//
//import java.time.LocalDate;
//import java.util.UUID;
//
//@Configuration
//public class AppConfig {
//
//    @Bean
//    public FlatFileItemReader<AssetRequestDto> assetReader() {
//        FlatFileItemReader<AssetRequestDto> reader = new FlatFileItemReader<>();
//        reader.setResource(new FileSystemResource("assets.csv"));
//        reader.setLinesToSkip(1);
//
//        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
//
////        String assetName,
////        AssetStatus status,
////        AssetHealth health,
////        LocalDate installedDate,
////        String location
//
//        tokenizer.setNames("assetName", "status", "health", "installedDate", "location");
//
//        BeanWrapperFieldSetMapper<AssetRequestDto> mapper =
//                new BeanWrapperFieldSetMapper<>();
//        mapper.setTargetType(AssetRequestDto.class);
//
//        DefaultLineMapper<AssetRequestDto> lineMapper = new DefaultLineMapper<>();
//        lineMapper.setLineTokenizer(tokenizer);
//        lineMapper.setFieldSetMapper(mapper);
//
//        reader.setLineMapper(lineMapper);
//        return reader;
//    }
//
//    @Bean
//    public ItemProcessor<Asset, Asset> assetProcessor() {
//        return asset -> {
//            asset.setAssetId(UUID.randomUUID().toString());
//            return asset;
//        };
//    }
//
//    @Bean
//    public JpaItemWriter<Asset> assetWriter(EntityManagerFactory emf) {
//        JpaItemWriter<Asset> writer = new JpaItemWriter<>();
//        writer.setEntityManagerFactory(emf);
//        return writer;
//    }
//
//    @Bean
//    public Step assetImportStep(StepBuilder stepBuilderFactory) {
//        return stepBuilderFactory.get("assetImportStep")
//                .<Asset, Asset>chunk(50)
//                .reader(assetReader())
//                .processor(assetProcessor())
//                .writer(assetWriter(null))
//                .build();
//    }
//
//    @Bean
//    public Job assetImportJob(JobBuilder jobBuilderFactory) {
//        return jobBuilderFactory.get("assetImportJob")
//                .start(assetImportStep(null))
//                .build();
//    }
//
//}
