package com.taskmanagement.backend.task.dtos.RequestDTO;
import java.time.LocalDateTime;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TaskRequestDTO {
    @Size(max = 100, message = "title must be less than 100 characters")
    @NotBlank(message = "title is required")
    private String title;
    @Size(max = 2000, message = "descrition must be less than 2000 character")
    private String description;
    private String status;
    private Long ownerId;
    private String priority;
    private LocalDateTime dueDate;
 }