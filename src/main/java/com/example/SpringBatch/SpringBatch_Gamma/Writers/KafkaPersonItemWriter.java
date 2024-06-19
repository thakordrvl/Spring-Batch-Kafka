package com.example.SpringBatch.SpringBatch_Gamma.Writers;

import com.example.SpringBatch.SpringBatch_Gamma.Model.Person;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class KafkaPersonItemWriter implements ItemWriter<Person> {

    @Autowired
    private final KafkaTemplate<String, Person> kafkaTemplate;

    @Value("${spring.kafka.template.default-topic}")
    private String topic;

    public KafkaPersonItemWriter(KafkaTemplate<String, Person> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void write(List<? extends Person> items) {
        for (Person person : items) {
            kafkaTemplate.send(topic, person.getLastname(), person);
        }
    }
}
