package com.savostov.kafka.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class Task {
    private Long id;
    private String title;
    private String description;
    private String status;
    private Long categoryId;
}
