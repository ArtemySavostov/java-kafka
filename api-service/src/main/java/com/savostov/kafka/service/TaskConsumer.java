package com.savostov.kafka.service;

import com.savostov.kafka.model.Task;
import com.savostov.kafka.repository.TaskRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class TaskConsumer {
    private final TaskRepository taskRepository;

    public TaskConsumer(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @KafkaListener(topics = "task-created", groupId = "task-group")
    public void listen (Task task){
        taskRepository.save(task);
    }

}
