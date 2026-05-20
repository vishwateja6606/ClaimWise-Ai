package com.claimwise.auth.dto;

import com.claimwise.auth.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RegisterRequest (
     @NotBlank(message="full name is required")
     String fullName,

     @Email(message ="email must be valid")
     @NotBlank(message ="email is required")
     String email,

    @Size(min = 8, message = "Password must be at least 8 characters")
    @NotBlank(message = "Password is required")
    String password,

    @NotNull(message = "Role is required")
    Role role
){

}
