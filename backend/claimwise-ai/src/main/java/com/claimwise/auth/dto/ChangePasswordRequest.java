package com.claimwise.auth.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ChangePasswordRequest (
@NotBlank(message="current password is required")
 String currentPassword,

@NotBlank(message = "New password is required")
@Size(min = 8, message = "New password must be at least 8 characters")
String newPassword


){
}
