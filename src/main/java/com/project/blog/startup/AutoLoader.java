package com.project.blog.startup;

import com.project.blog.enums.Roles;
import com.project.blog.model.Role;
import com.project.blog.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AutoLoader implements CommandLineRunner {
    private final RoleRepository roleRepository;
    @Override
    public void run(String... args) throws Exception {
        if(roleRepository.count() == 0){
            for(Roles r : Roles.values()){
                Role role = new Role();
                role.setName(r);
                roleRepository.save(role);
            }
        }
    }
}
