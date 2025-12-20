package com.learnspring.dto.request;

import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor // constructor with no parameter
public class UserCreationRequest {
    private String username;
    @Size(min = 5, max = 10 , message = "USERNAME_INVALID")
    private String password;
    private String firstName;
    private String lastName;
    private LocalDate dob;

}
