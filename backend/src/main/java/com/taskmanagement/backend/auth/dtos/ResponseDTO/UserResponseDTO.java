package com.taskmanagement.backend.auth.dtos.ResponseDTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponseDTO {
    private Long id;
    private String email;
    private String displayName;
    private String role;
}