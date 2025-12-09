package com.learnspring.mapper;

import com.learnspring.dto.request.UserCreationRequest;
import com.learnspring.dto.request.UserUpdateRequest;
import com.learnspring.dto.response.UserResponse;
import com.learnspring.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import java.util.*;
@Mapper(componentModel = "spring") // Bean : Spring implements UserMapper
public interface UserMapper {
    User toDtoUser(UserCreationRequest request); // DTO -> Entity : to receive request from client
    // @MappingTarget : allows directly update object without creating new object
    void updateUser(@MappingTarget User user, UserUpdateRequest request);
    List<UserResponse> toUserListResponse(List<User> user);
    UserResponse toUserResponse(User user);
}
