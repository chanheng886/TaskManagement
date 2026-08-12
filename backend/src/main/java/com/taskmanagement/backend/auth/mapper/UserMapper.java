package com.taskmanagement.backend.auth.mapper;
import org.springframework.stereotype.Component;
import com.taskmanagement.backend.auth.dtos.RequestDTO.UserRequestDTO;
import com.taskmanagement.backend.auth.dtos.ResponseDTO.UserResponseDTO;
import com.taskmanagement.backend.auth.entity.User;
import com.taskmanagement.backend.auth.enums.Roles;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserMapper {
    public User toEntity(UserRequestDTO dto){
        return User.builder()
            .displayName(dto.getDisplayName())
            .email(dto.getEmail())
            .passwordHash(dto.getPasswordHash())
            .roles(Roles.USER)
            .build();
    }

    public User toUpdate(User user, UserRequestDTO dto){
        user.setDisplayName(dto.getDisplayName());
        user.setEmail(dto.getEmail());
        user.setPasswordHash(dto.getPasswordHash());
        return user;
    }

    public UserResponseDTO toResponse(User user){
        return UserResponseDTO.builder()
            .id(user.getId())
            .displayName(user.getDisplayName())
            .email(user.getEmail())
            // .role(user.getRoles() != null ? user.getRoles().name() : "USER")
            .role(user.getRoles().name())
            .build();
    }
}