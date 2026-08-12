package com.taskmanagement.backend.task.mapper;
import java.time.LocalDateTime;
import org.springframework.stereotype.Component;
import com.taskmanagement.backend.auth.entity.User;
import com.taskmanagement.backend.auth.repository.UserRepository;
import com.taskmanagement.backend.task.dtos.RequestDTO.TaskRequestDTO;
import com.taskmanagement.backend.task.dtos.ResponseDTO.TaskResponseDTO;
import com.taskmanagement.backend.task.entity.Task;
import com.taskmanagement.backend.task.enums.Priority;
import com.taskmanagement.backend.task.enums.Status;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TaskMapper {
    private final UserRepository repository;
    public Task toEntity(TaskRequestDTO dto){
        User owner = repository.findById(dto.getOwnerId()).orElseThrow(() -> new RuntimeException("User not found!"));
        return Task.builder()
        .title(dto.getTitle())
        .description(dto.getDescription())
        .status(Status.valueOf(dto.getStatus()))
        .priority(Priority.valueOf(dto.getPriority()))
        .dueDate(dto.getDueDate())
        .user(owner)
        .build();
    }

    public Task toUpdate(Task task, TaskRequestDTO dto){
        User owner = repository.findById(dto.getOwnerId()).orElseThrow(() -> new RuntimeException("User not found!"));
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        try{
            task.setStatus(Status.valueOf(dto.getStatus()));
        }catch(IllegalArgumentException e){
            throw new RuntimeException("Invalid status: " + dto.getStatus());
        }
        try{
            task.setPriority(Priority.valueOf(dto.getPriority()));
        }catch(IllegalArgumentException e){
            throw new RuntimeException("Ivalid Priority: " + dto.getPriority());
        }
        task.setDueDate(dto.getDueDate());
        task.setUpdatedAt(LocalDateTime.now());
        task.setUser(owner);
        return task;
    }

    public TaskResponseDTO toResponse(Task task){
        return TaskResponseDTO.builder()
            .id(task.getId())
            .ownerId(task.getUser().getId())
            .title(task.getTitle())
            .description(task.getDescription())
            .status(task.getStatus().name())
            .priority(task.getPriority().name())
            .dueDate(task.getDueDate())
            .createdAt(task.getCreatedAt())
            .updatedAt(task.getUpdatedAt())
            .build();
    }
}