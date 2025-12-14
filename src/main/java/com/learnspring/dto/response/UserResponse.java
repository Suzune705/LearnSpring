package com.learnspring.dto.response;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {
    private int id;
    private String username;
    private String firstName;
    private String lastName;
    private LocalDate dob;
    private Set<String> roles ;
 }

