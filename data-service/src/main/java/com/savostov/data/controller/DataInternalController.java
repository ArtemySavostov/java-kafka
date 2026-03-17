package com.savostov.data.controller;

import com.savostov.data.model.Task;
import com.savostov.data.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/internal")
public class DataInternalController {
    private final TaskRepository taskRepository;

    public DataInternalController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping("/search")
    public List<Task> search(@RequestParam String title) {
        return taskRepository.findByTitleContainingIgnoreCase(title);
    }

    @GetMapping("/reports")
    public Map<String, Object> getReports() {
        Map<String, Object> reports = new HashMap<>();
        reports.put("by_status", taskRepository.getStatusReport());
        reports.put("by_category", taskRepository.getCategoryReport());
        reports.put("heavy_categories", taskRepository.getHeavyCategoriesReport());
        return reports;
    }


}
