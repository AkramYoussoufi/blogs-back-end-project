package com.project.blog.dto;

import com.project.blog.enums.Roles;
import com.project.blog.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SignupResponse {
    private Long userId;
    private Collection<? extends GrantedAuthority> roles;
    private String JWT;
    private String message;
}
