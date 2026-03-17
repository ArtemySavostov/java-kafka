package com.savostov.kafka.service;

import com.savostov.kafka.dto.TaskDto;
import com.savostov.kafka.model.Task;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service

public class TaskProducer {

    private final KafkaTemplate<String, TaskDto> kafkaTemplate;

    public TaskProducer(KafkaTemplate<String, TaskDto> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendTaskCreated(TaskDto taskDto) {
        kafkaTemplate.send("task-created", taskDto);
    }
}