package com.project.blog.service;

import com.project.blog.controller.AuthController;
import com.project.blog.dto.RegisterRequest;
import com.project.blog.dto.SignupRequest;
import com.project.blog.enums.Roles;
import com.project.blog.model.Role;
import com.project.blog.model.User;
import com.project.blog.repository.RoleRepository;
import com.project.blog.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final AuthenticationProvider authenticationProvider;
    private final UserDetailsServiceImp userDetailsServiceImp;
    private final JWTService jwtService;


    public void signup(RegisterRequest registerRequest) {
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(registerRequest.getPassword()));
        user.setEmail(registerRequest.getEmail());
        user.getRoles().add(roleRepository.findByName(Roles.USER).get());

        userRepository.save(user);
    }

    public String authenticateUser(SignupRequest signupRequest) {
            User user = userRepository.findByEmail(signupRequest.getEmail()).get();
            UserDetails userDetails = userDetailsServiceImp.loadUserByUsername(user.getUsername());
            Authentication authToken = new UsernamePasswordAuthenticationToken(userDetails, signupRequest.getPassword(), userDetails.getAuthorities());
            Authentication authentication = authenticationProvider.authenticate(authToken);
            if(authentication.isAuthenticated()){
                return jwtService.generateToken(user.getId());
            }

            return null;
    }

    public Optional<User> getCurrentUser() {
        //Getting the authenticated user based on the thread request that is authenticated
        return Optional.of((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }
}
