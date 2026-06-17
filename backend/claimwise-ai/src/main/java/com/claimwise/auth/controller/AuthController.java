package com.claimwise.auth.controller;

import com.claimwise.auth.dto.*;
import com.claimwise.auth.entity.User;
import com.claimwise.auth.service.AuthService;
import com.claimwise.common.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
@Tag(name = "Auth", description = "Authentication APIs for ClaimWise AI")
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    @Operation(summary = "Register a new user")
@PostMapping("/register")
  public ApiResponse<UserResponse> register(@Valid @RequestBody RegisterRequest request){
    UserResponse response = authService.register(request);
    return ApiResponse.success("User registered successfully", response);
}
@Operation(summary = "Login and generate JWT token")
 @PostMapping("/login")
    public ApiResponse<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        return ApiResponse.success("Login successful", authService.login(request));
    }

    @Operation(summary = "Get current logged-in user")
    @GetMapping("/me")
    public ApiResponse<UserResponse> me(Authentication authentication) {
        return ApiResponse.success(
                "Current user fetched successfully",
                authService.getCurrentUser(authentication.getName())
        );
    }

    @Operation(summary = "Change the current password")
    @PutMapping("/change-password")
    public ResponseEntity<ApiResponse<Void>> changePassword(
            @Valid @RequestBody ChangePasswordRequest request,
            Authentication authentication
    ) {
        authService.changePassword(request, authentication.getName());
        return ResponseEntity.ok(new ApiResponse<>(true, "Password changed successfully", null));
    }


}
