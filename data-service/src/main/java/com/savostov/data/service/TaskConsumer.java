package com.savostov.data.service;

import com.savostov.data.dto.TaskDto;
import com.savostov.data.model.Category;
import com.savostov.data.model.Task;
import com.savostov.data.repository.CategoryRepository;
import com.savostov.data.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TaskConsumer {
    private final TaskRepository taskRepository;
    private final CategoryRepository categoryRepository;

    public TaskConsumer(TaskRepository taskRepository, CategoryRepository categoryRepository) {
        this.taskRepository = taskRepository;
        this.categoryRepository = categoryRepository;
    }

    @KafkaListener(topics = "task-created", groupId = "task-group")
    public void consume(TaskDto taskDto) {
//        log.info("Received task from Kafka: {}", taskDto);

        Task task = new Task();
        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        task.setStatus(taskDto.getStatus());

        String catName = (taskDto.getCategoryName() != null) ? taskDto.getCategoryName() : "General";
        Category category = categoryRepository.findByName(catName)
                .orElseGet(() -> {
                    Category newCat = new Category();
                    newCat.setName(catName);
                    return categoryRepository.save(newCat);
                });

        task.setCategory(category);
        taskRepository.save(task);
    }
}
