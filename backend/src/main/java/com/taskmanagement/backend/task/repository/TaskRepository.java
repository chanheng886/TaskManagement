package com.taskmanagement.backend.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taskmanagement.backend.task.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

    
}