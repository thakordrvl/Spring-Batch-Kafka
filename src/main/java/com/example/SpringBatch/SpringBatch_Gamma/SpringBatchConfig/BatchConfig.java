package com.example.SpringBatch.SpringBatch_Gamma.SpringBatchConfig;

import com.example.SpringBatch.SpringBatch_Gamma.Model.Person;
import com.example.SpringBatch.SpringBatch_Gamma.Processors.PersonItemProcessor;
import com.example.SpringBatch.SpringBatch_Gamma.Readers.PersonItemReader;
import com.example.SpringBatch.SpringBatch_Gamma.Writers.KafkaPersonItemWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Bean
    public Job importUserJob(JobBuilderFactory jobBuilderFactory, Step step1) {
        return jobBuilderFactory.get("importUserJob")
                .start(step1)
                .build();
    }

    @Bean
    public Step step1(StepBuilderFactory stepBuilderFactory, PersonItemReader reader, PersonItemProcessor processor, KafkaPersonItemWriter writer) {
        return stepBuilderFactory.get("step1")
                .<Person, Person>chunk(10)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }
}
