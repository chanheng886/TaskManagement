package com.taskmanagement.backend.auth.dtos.RequestDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDTO {
    @Size(max = 50, message = "email should be less then 50 character")
    @NotBlank(message = "Email is required")
    private String email;

    @Size(max = 100, message = "Username is too long, should be less than 100 characters")
    private String displayName;

    @NotNull(message = "Password is required")
    @Size(max = 100, message = "Password is too long")
    private String passwordHash;
}