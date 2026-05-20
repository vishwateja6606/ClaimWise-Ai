package com.claimwise.auth.dto;

import com.claimwise.auth.enums.Role;

public record UserResponse(
        Long id,
        String fullName,
        String email,
        Role role

)
{

}

