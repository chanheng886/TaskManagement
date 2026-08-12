package com.taskmanagement.backend.task.controller;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.taskmanagement.backend.task.dtos.RequestDTO.TaskRequestDTO;
import com.taskmanagement.backend.task.dtos.ResponseDTO.TaskResponseDTO;
import com.taskmanagement.backend.task.services.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/task")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService service;
    @GetMapping
    public List<TaskResponseDTO> getAllTask(){
        return service.getAllTask();
    }
    @PostMapping
    public TaskResponseDTO createTask(@Valid @RequestBody TaskRequestDTO dto){
        return service.createTask(dto);
    }

    @PutMapping("/update/{id}")
    public TaskResponseDTO updateTask(@PathVariable Long id, @RequestBody TaskRequestDTO dto){
        return service.updateTask(id, dto);
    }

    @DeleteMapping("/delete/{id}")
    public TaskResponseDTO deleteTask(@PathVariable Long id){
        return service.deleteTask(id);
    }
}