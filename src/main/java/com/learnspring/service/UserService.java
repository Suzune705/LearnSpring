package com.learnspring.service;

import com.learnspring.dto.request.UserCreationRequest;
import com.learnspring.dto.request.UserUpdateRequest;
import com.learnspring.dto.response.UserResponse;
import com.learnspring.exception.AppException;
import com.learnspring.exception.ErrorCode;
import com.learnspring.mapper.UserMapper;
import com.learnspring.model.User;
import com.learnspring.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor // only includes final field
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper ;

    public User createUserRequest(UserCreationRequest userRequest) {
        if(userRepository.existsByUsername(userRequest.getUsername())) throw new AppException(ErrorCode.USER_EXISTED);
        User user = userMapper.toDtoUser(userRequest);
        // insert to database
        return userRepository.save(user);
    }

    public List<UserResponse> getAllUser(){
        return userMapper.toUserListResponse(userRepository.findAll()); // Entity -> DTO
    }
    public List<UserResponse> getAllUserByUsername(String username) {
        if(userRepository.findAllByUsername(username).isEmpty()){
            throw new RuntimeException("User not found");
        }
        return userMapper.toUserListResponse(userRepository.findAllByUsername(username)); // Entity -> DTO
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
