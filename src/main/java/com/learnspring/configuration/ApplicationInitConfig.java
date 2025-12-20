package com.learnspring.configuration;

import com.learnspring.model.Role;
import com.learnspring.model.User;
import com.learnspring.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Configuration

/*
    @ApplicationRunner :
 */
public class ApplicationInitConfig {
    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository){
        Optional<User> user = userRepository.findByUsername("admin");
        return  args ->  {
            if(user.isEmpty()){
                Set<Role> roles = new HashSet<>();
                Role role = Role.builder()
                        .name("ADMIN")
                        .build();
                roles.add(role);
                User admin = User.builder()
                        .username("admin")
                        .password("123456")
                        .roles(roles)
                        .build();
                userRepository.save(admin);
            }
        };
    }
}
