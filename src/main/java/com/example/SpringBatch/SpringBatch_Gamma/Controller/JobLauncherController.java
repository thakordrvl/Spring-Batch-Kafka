package com.example.SpringBatch.SpringBatch_Gamma.Controller;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/joblauncher")
public class JobLauncherController {
    private final JobLauncher jobLauncher;
    private final Job job; // Inject your Spring Batch job bean here

    public JobLauncherController(JobLauncher jobLauncher, Job job) {
        this.jobLauncher = jobLauncher;
        this.job = job;
    }

    @GetMapping("/job1")
    public String handle() throws Exception {
        try {
            JobParameters jobParameters = new JobParametersBuilder()
                    .addLong("time", System.currentTimeMillis())
                    .toJobParameters();
            jobLauncher.run(job, jobParameters);
        } catch (Exception e) {
            return "Job failed: " + e.getMessage();
        }

        return "Job completed successfully";
    }
}
