package com.savostov.kafka.service;

import com.savostov.kafka.model.Task;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class TaskProducer {
    private final KafkaTemplate<String, Task> kafkaTemplate;

    public TaskProducer (KafkaTemplate<String, Task> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
    public void sendTaskCreated (Task task) {
        kafkaTemplate.send("task-created", task);
    }
}
