package com.learnspring.service;

import com.learnspring.dto.request.UserCreationRequest;
import com.learnspring.dto.request.UserUpdateRequest;
import com.learnspring.dto.response.UserResponse;
import com.learnspring.exception.AppException;
import com.learnspring.enums.ErrorCodeType;
import com.learnspring.mapper.UserMapper;
import com.learnspring.model.User;
import com.learnspring.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
@RequiredArgsConstructor // only includes final field
@EnableMethodSecurity
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper ;

    public User createUserRequest(UserCreationRequest userRequest) {
        if(userRepository.existsByUsername(userRequest.getUsername())) throw new AppException(ErrorCodeType.USER_EXISTED);
        User user = userMapper.toDtoUser(userRequest);
        Set<String> role = new HashSet<>();
        role.add("USER");
//        user.setRoles(role);
        // insert to database
        return userRepository.save(user);
    }
//    @PreAuthorize("hasRole('ADMIN')") // authorize before method
    public List<UserResponse> getAllUser(){
        return userMapper.toUserListResponse(userRepository.findAll()); // Entity -> DTO
    }
    @PreAuthorize("#username == authentication.name") // authorize after run method
    public List<UserResponse> getAllUserByUsername(String username) {
        if(userRepository.findAllByUsername(username).isEmpty()){
            throw new RuntimeException("User not found");
        }
        return userMapper.toUserListResponse(userRepository.findAllByUsername(username)); // Entity -> DTO
    }

    public UserResponse getMyInfo(){
        SecurityContext contextHolder = SecurityContextHolder.getContext();
        String name = contextHolder.getAuthentication().getName();
        User user = userRepository.findByUsername(name).orElseThrow(
                () -> new AppException(ErrorCodeType.USER_NOT_EXISTED)
        );
        return userMapper.toUserResponse(user);
    }
    public UserResponse updateUser(int userID, UserUpdateRequest request){
        User u  = userRepository.findById(userID).orElseThrow(() -> new RuntimeException("User not found"));
        this.userMapper.updateUser(u, request); // update object in memory
        return userMapper.toUserResponse(userRepository.save(u)); // save to database
    }
    public void deleteUser(int userID){
        userRepository.deleteById(userID);
    }
}
