package com.learnspring.controller;

import com.learnspring.dto.request.ApiResponse;
import com.learnspring.dto.request.UserCreationRequest;
import com.learnspring.dto.request.UserUpdateRequest;
import com.learnspring.dto.response.UserResponse;
import com.learnspring.model.User;
import com.learnspring.service.UserService;
import jakarta.persistence.PreUpdate;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    ApiResponse<User> createUser(@RequestBody @Valid UserCreationRequest request){
        ApiResponse<User> apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.createUserRequest(request));
       return apiResponse;
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/getAll")
    public List<UserResponse> getAllUser(){
        return userService.getAllUser();
    }

    @GetMapping("/search/{userName}")
    public List<UserResponse> getUserByName(@PathVariable String userName){
        return userService.getAllUserByUsername(userName);
    }
    @GetMapping("/getU")
    ApiResponse<List<UserResponse>> getUsers(){
         Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
        log.info("user name : {}",authentication.getName());
        log.info("user name : {}",authentication.getAuthorities()); // get role
        authentication.getAuthorities().forEach(grantedAuthority ->
                log.info(grantedAuthority.getAuthority())
        );
        return ApiResponse.<List<UserResponse>>builder()
                .result(userService.getAllUser())
                .build();
    }

    @PutMapping("/update/{userID}")
    public UserResponse updateUserByID(@PathVariable int userID, @RequestBody UserUpdateRequest request){
       return userService.updateUser(userID, request);
    }
    @GetMapping("/myInfo")
    public ApiResponse<UserResponse> getMyInfo(){
        return ApiResponse.<UserResponse>builder()
                .result(userService.getMyInfo())
                .build();
    }
    @DeleteMapping("/delete/{userID}")
    public void deleteUserByID(@PathVariable int userID){
        userService.deleteUser(userID);
    }
}
