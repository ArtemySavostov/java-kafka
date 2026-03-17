package com.savostov.data.repository;

import com.savostov.data.model.Category;
import com.savostov.data.model.Task;

import lombok.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByTitleContainingIgnoreCase(String title);

//Группировка по статусам (Сколько задач в 'NEW', 'DONE' и т.д.)
    @Query("SELECT t.status, COUNT(t) FROM Task t GROUP BY t.status")
    List<Object[]> getStatusReport();

//Количество задач в каждой категории
    @Query("SELECT c.name, COUNT(t) FROM Category c LEFT JOIN c.tasks t GROUP BY c.name")
    List<Object[]> getCategoryReport();

// Список категорий, в которых более 3-х задач (нагруженные категории)
    @Query("SELECT c.name FROM Category c JOIN c.tasks t GROUP BY c.name HAVING COUNT(t) > 3")
    List<String> getHeavyCategoriesReport();

}
