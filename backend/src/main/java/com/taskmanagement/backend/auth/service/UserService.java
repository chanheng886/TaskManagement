package com.taskmanagement.backend.auth.service;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.taskmanagement.backend.auth.dtos.RequestDTO.UserRequestDTO;
import com.taskmanagement.backend.auth.dtos.ResponseDTO.UserResponseDTO;
import com.taskmanagement.backend.auth.entity.User;
import com.taskmanagement.backend.auth.enums.Roles;
import com.taskmanagement.backend.auth.mapper.UserMapper;
import com.taskmanagement.backend.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper mapper;
    private final UserRepository repository;

    public List<UserResponseDTO> getAllUser(){
        return repository.findAll()
            .stream()
            .map(mapper::toResponse)
            .collect(Collectors.toList());
    }
    
    public UserResponseDTO createUser(UserRequestDTO dto){
        User user = mapper.toEntity(dto);
        User save = repository.save(user);
        return mapper.toResponse(save);
    }

    public UserResponseDTO updateUser(Long id, UserRequestDTO dto){
        User user = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("User with id: " + id + " Not found!"));
        
        User update = mapper.toUpdate(user, dto);
        User save = repository.save(update);
        return mapper.toResponse(save);
    }

    public UserResponseDTO promoteUserToAdmin(Long id){
        User user = repository.findById(id).orElseThrow(()-> new RuntimeException("User with id: " + id + "Not Found!"));
        user.setRoles(Roles.ADMIN);
        User updateUser = repository.save(user);
        return mapper.toResponse(updateUser);
    }

    public UserResponseDTO deleteUser(Long id){
        User user = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("User with id: " + id + " Not Found!"));
        repository.delete(user);
        return mapper.toResponse(user);
    }
}
