package com.taskmanagement.backend.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taskmanagement.backend.auth.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    
}