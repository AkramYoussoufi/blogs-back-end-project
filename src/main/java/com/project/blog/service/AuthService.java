package com.project.blog.service;

import com.project.blog.controller.AuthController;
import com.project.blog.dto.RegisterRequest;
import com.project.blog.model.User;
import com.project.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;

    @Autowired
    public AuthService(BCryptPasswordEncoder bCryptPasswordEncoder, UserRepository userRepository){
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRepository = userRepository;
    }
    public void signup(RegisterRequest registerRequest) {
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(registerRequest.getPassword()));
        user.setEmail(registerRequest.getEmail());

        userRepository.save(user);
    }
}
