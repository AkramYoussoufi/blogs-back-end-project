package com.project.blog.service;

import com.project.blog.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class UserDetailsServiceImp implements UserDetailsService {

    UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try{
            return userRepository.findByUsername(username).get();
        }catch (UsernameNotFoundException | NoSuchElementException ex){
            throw new UsernameNotFoundException("Couldn't retrieve the user");
        }
    }
}
