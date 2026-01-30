//package com.greenko.assetmanagement.batch.api;
//
//import org.springframework.batch.core.job.Job;
//import org.springframework.batch.core.job.parameters.JobParameters;
//import org.springframework.batch.core.job.parameters.JobParametersBuilder;
//
//import org.springframework.batch.core.launch.JobLauncher;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/admin/batch")
//public class BatchAssetController {
//
//    @Autowired
//    JobLauncher jobLauncher;
//
//    @Autowired
//    Job assetImportJob;
//
//    @PostMapping("/import-assets")
//    public String runJob() throws Exception {
//        JobParameters params = new JobParametersBuilder()
//                .addLong("startAt", System.currentTimeMillis())
//                .toJobParameters();
//
//        jobLauncher.run(assetImportJob, params);
//        return "Asset import started";
//    }
//}
