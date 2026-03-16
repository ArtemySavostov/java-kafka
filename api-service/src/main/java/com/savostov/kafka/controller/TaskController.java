package com.savostov.kafka.controller;

import com.savostov.kafka.model.Task;
import com.savostov.kafka.service.TaskProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskProducer taskProducer;
    private final RestTemplate restTemplate;

    private final String DATA_SERVICE_URL="http://data-service:8081/internal";


    @PostMapping
    public ResponseEntity<String> createTask(@RequestBody Task task){
        taskProducer.sendTaskCreated(task);
        return ResponseEntity.ok("Task sent to processing");
    }

    @GetMapping("/search")
    public ResponseEntity<Object> searchTasks(@RequestParam String query){
        String url = DATA_SERVICE_URL + "/search?title="+query;
        Object response = restTemplate.getForObject(url, Object.class);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/reports")
    public ResponseEntity<Object> getReports(){
        String url = DATA_SERVICE_URL + "/reports";
        Object response = restTemplate.getForObject(url, Object.class);
        return ResponseEntity.ok(response);
    }

}
