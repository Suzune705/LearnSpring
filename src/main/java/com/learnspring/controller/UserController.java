package com.learnspring.controller;

import com.learnspring.dto.request.ApiResponse;
import com.learnspring.dto.request.UserCreationRequest;
import com.learnspring.dto.request.UserUpdateRequest;
import com.learnspring.dto.response.UserResponse;
import com.learnspring.model.User;
import com.learnspring.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/create")
    ApiResponse<User> createUser(@RequestBody @Valid UserCreationRequest request){
        ApiResponse<User> apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.createUserRequest(request));
       return apiResponse;
    }

    @GetMapping("/getAll")
    public List<UserResponse> getAllUser(){
        return userService.getAllUser();
    }

    @GetMapping("/search/{userName}")
    public List<UserResponse> getUserByName(@PathVariable String userName){
        return userService.getAllUserByUsername(userName);
    }

    @PutMapping("/update/{userID}")
    public UserResponse updateUserByID(@PathVariable int userID, @RequestBody UserUpdateRequest request){
       return userService.updateUser(userID, request);
    }
    @DeleteMapping("/delete/{userID}")
    public void deleteUserByID(@PathVariable int userID){
        userService.deleteUser(userID);
    }
}
