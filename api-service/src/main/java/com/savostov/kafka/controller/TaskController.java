package com.savostov.kafka.controller;

import com.savostov.kafka.dto.TaskDto;
import com.savostov.kafka.model.Task;
import com.savostov.kafka.service.DataServiceClient;
import com.savostov.kafka.service.TaskProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskProducer taskProducer;
    private final DataServiceClient dataServiceClient;

    @PostMapping
    public ResponseEntity<String> createTask(@RequestBody TaskDto task){
        taskProducer.sendTaskCreated(task);
        return ResponseEntity.ok("Task sent to processing");
    }

    @GetMapping("/search")
    public ResponseEntity<Object> searchTasks(@RequestParam String query){
        Object response = dataServiceClient.searchTasks(query);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/reports")
    public ResponseEntity<Object> getReports(){
        Object response = dataServiceClient.getReports();
        return ResponseEntity.ok(response);
    }

}
