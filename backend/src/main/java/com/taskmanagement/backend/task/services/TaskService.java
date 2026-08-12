package com.taskmanagement.backend.task.services;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

import com.taskmanagement.backend.task.dtos.RequestDTO.TaskRequestDTO;
import com.taskmanagement.backend.task.dtos.ResponseDTO.TaskResponseDTO;
import com.taskmanagement.backend.task.entity.Task;
import com.taskmanagement.backend.task.mapper.TaskMapper;
import com.taskmanagement.backend.task.repository.TaskRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskMapper taskMapper;
    private final TaskRepository taskRepository;

    public List<TaskResponseDTO> getAllTask(){
        return taskRepository.findAll()
            .stream()
            .map(taskMapper::toResponse)
            .collect(Collectors.toList());
    }

    public TaskResponseDTO createTask(TaskRequestDTO dto){
        Task task = taskMapper.toEntity(dto);
        Task save = taskRepository.save(task);
        return taskMapper.toResponse(save);
    }

    public TaskResponseDTO updateTask(Long id, TaskRequestDTO dto){
        Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task with id: " + id + " Not Found!"));
        Task update = taskMapper.toUpdate(task, dto);
        Task save = taskRepository.save(update);
        return taskMapper.toResponse(save);
    }

    public TaskResponseDTO deleteTask(Long id){
        Task task = taskRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Task id: " + id + " not found"));
        taskRepository.delete(task);
        return taskMapper.toResponse(task);
    }
}
