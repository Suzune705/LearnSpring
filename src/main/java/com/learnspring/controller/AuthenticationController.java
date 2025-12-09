package com.learnspring.controller;

import com.learnspring.dto.request.ApiResponse;
import com.learnspring.dto.request.AuthenticationRequest;
import com.learnspring.dto.response.AuthenticationResponse;
import com.learnspring.service.AuthenticationService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;


@RestController
@RequestMapping("/auth")  // all endpoints in this class will start with "auth"
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService ;
    @PostMapping("/user/log-in")
    ApiResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
        boolean check = authenticationService.authenticate(request);
        var auth = AuthenticationResponse.builder()
                .authenticated(check)
                .build();

        return ApiResponse.<AuthenticationResponse>builder() // create Builder
                .result(auth) // set result
                .build(); // create ApiResponse object and copy Builder's value to ApiResponse's value
    }
}
