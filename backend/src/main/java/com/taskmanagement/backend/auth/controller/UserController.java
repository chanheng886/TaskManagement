package com.taskmanagement.backend.auth.controller;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.taskmanagement.backend.auth.dtos.RequestDTO.UserRequestDTO;
import com.taskmanagement.backend.auth.dtos.ResponseDTO.UserResponseDTO;
import com.taskmanagement.backend.auth.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequestMapping("/api/v1/user")
@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService service;

    @GetMapping
    public List<UserResponseDTO> getAllUser(){
        return service.getAllUser();
    }

    @PostMapping
    public UserResponseDTO createUser(@Valid @RequestBody UserRequestDTO dto){
        return service.createUser(dto);
    }

    @PutMapping("/update/{id}")
    public UserResponseDTO updateUser(@PathVariable Long id, @RequestBody UserRequestDTO dto){
        return service.updateUser(id, dto);
    }

    @DeleteMapping("/delete/{id}")
    public UserResponseDTO deleteUser(@PathVariable Long id){
        return service.deleteUser(id);
    }

    @PutMapping("/promote/{id}")
    public ResponseEntity<UserResponseDTO> promoteUser(@PathVariable Long id){
        UserResponseDTO promoted = service.promoteUserToAdmin(id);
        return ResponseEntity.ok(promoted);
    }

}
