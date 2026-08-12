package com.taskmanagement.backend.auth.entity;

import com.taskmanagement.backend.auth.enums.Roles;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;


    @Column(name = "password_hash", nullable = false, unique = true)
    private String passwordHash;

    @Column(name = "display_name")
    private String displayName;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Roles roles = Roles.USER;    
}