package com.learnspring.configuration;

import com.learnspring.enums.Role;
import com.learnspring.model.User;
import com.learnspring.repository.UserRepository;
import jdk.jfr.Category;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.actuate.autoconfigure.wavefront.WavefrontProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Configuration

/*
    @ApplicationRunner :
 */
public class ApplicationInitConfig {
    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository){
        return  args ->  {
            if(userRepository.findByUsername("admin").isEmpty()){
                Set<String> role = new HashSet<>();
                role.add(Role.ADMIN.name());
                User admin = User.builder()
                        .username("admin")
                        .password("123456")
                        .roles(role)
                        .build();
                userRepository.save(admin);
            }
        };
    }
}
