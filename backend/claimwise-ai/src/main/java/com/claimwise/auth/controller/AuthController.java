package com.claimwise.auth.controller;

import com.claimwise.auth.dto.AuthResponse;
import com.claimwise.auth.dto.LoginRequest;
import com.claimwise.auth.dto.RegisterRequest;
import com.claimwise.auth.dto.UserResponse;
import com.claimwise.auth.entity.User;
import com.claimwise.auth.service.AuthService;
import com.claimwise.common.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
@PostMapping("/register")
  public ApiResponse<UserResponse> register(@Valid @RequestBody RegisterRequest request){
    UserResponse response = authService.register(request);
    return ApiResponse.success("User registered successfully", response);
}
 @PostMapping("/login")
    public ApiResponse<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        return ApiResponse.success("Login successful", authService.login(request));
    }



}
