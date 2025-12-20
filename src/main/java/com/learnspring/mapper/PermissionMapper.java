package com.learnspring.mapper;


import com.learnspring.dto.request.PermissionRequest;
import com.learnspring.dto.response.PermissionResponse;
import com.learnspring.dto.response.UserResponse;
import com.learnspring.model.Permission;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
public class PermissionMapper {
    public Permission toPermission(PermissionRequest request) {
        return Permission.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();
    }
    public  PermissionResponse toPermissionResponse(Permission permission ){
        return PermissionResponse.builder()
                .name(permission.getName())
                .description(permission.getDescription())
                .build();
    }
}
