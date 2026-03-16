package com.savostov.kafka.controller;

import com.savostov.kafka.model.Task;
import com.savostov.kafka.repository.TaskRepository;
import com.savostov.kafka.service.TaskProducer;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskRepository taskRepository;
    private final TaskProducer taskProducer;


    public TaskController(TaskRepository taskRepository, TaskProducer taskProducer) {
        this.taskRepository = taskRepository;
        this.taskProducer = taskProducer;
    }

    @PostMapping
    public Task createTask(@RequestBody Task task) {
        Task savedTask = taskRepository.save(task);
        taskProducer.sendTaskCreated(task);
        return savedTask;
    }
    @GetMapping
    public List<Task> getAllTask(){
        return taskRepository.findAll();
    }

}
