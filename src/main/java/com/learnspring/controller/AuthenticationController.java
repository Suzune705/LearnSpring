package com.learnspring.controller;

import com.learnspring.dto.request.ApiResponse;
import com.learnspring.dto.request.AuthenticationRequest;
import com.learnspring.dto.request.IntrospectRequest;
import com.learnspring.dto.response.AuthenticationResponse;
import com.learnspring.dto.response.IntrospectResponse;
import com.learnspring.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")  // all endpoints in this class will start with "auth"
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService ;
    @PostMapping("/user/log-in")
    ApiResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
        AuthenticationResponse check = authenticationService.authenticate(request);
        return ApiResponse.<AuthenticationResponse>builder() // create Builder
                .result(check) // set result
                .build(); // create ApiResponse object and copy Builder's value to ApiResponse's value
    }
    @PostMapping("/user/introspect")
    ApiResponse<IntrospectResponse> authenticate(@RequestBody IntrospectRequest request){
        IntrospectResponse check = authenticationService.introspect(request);
        return  ApiResponse.<IntrospectResponse>builder()
                .result(check)
                .build();
    }
}
