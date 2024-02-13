package com.joaovellenich.Authentication.modules.user.dto;

import com.joaovellenich.Authentication.modules.user.UserRoles;

public record RegisterDTO(String email, String password, String name, UserRoles role) {
}
