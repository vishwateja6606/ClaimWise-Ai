package com.claimwise.auth.service;

import com.claimwise.auth.dto.AuthResponse;
import com.claimwise.auth.dto.LoginRequest;
import com.claimwise.auth.dto.RegisterRequest;
import com.claimwise.auth.dto.UserResponse;
import com.claimwise.auth.entity.User;
import com.claimwise.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public UserResponse register(RegisterRequest request){
        String email= request.email().toLowerCase();
        if(userRepository.existsByEmail(email)){
            throw new RuntimeException("email already exits");
        }
        User user = new User();
        user.setFullName(request.fullName());
        user.setEmail(request.email());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setRole(request.role());

        User saveduser = userRepository.save(user);
        return toResponse(saveduser);
    }
    public UserResponse toResponse(User user){
        return new UserResponse(
                user.getId(),
                user.getFullName(),
                user.getEmail(),

                user.getRole()
        );

    }
    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        );

        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new RuntimeException("User not found"));

        String token = jwtService.generateToken(user);

        return new AuthResponse(token, "Bearer", toResponse(user));
    }


}
