package com.learnspring.mapper;

import com.learnspring.dto.request.RoleRequest;
import com.learnspring.dto.response.PermissionResponse;
import com.learnspring.dto.response.RoleResponse;
import com.learnspring.model.Permission;
import com.learnspring.model.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Component
public class RoleMapper {

    public Role toRoleModel(RoleRequest request, List<Permission> list){
        Set<Permission> set = new HashSet<>(list);
        Role role = Role.builder()
                .name(request.getName())
                .description(request.getDescription())
                .permission(set)
                .build();
        return role ;
    }
    public  RoleResponse toRoleDTO(Role role){
        Set<PermissionResponse> responses = role.getPermission().stream()
                .map(p ->
                                PermissionResponse.builder()
                                        .name(p.getName())
                                        .description(p.getDescription())
                                        .build()
                ).collect(Collectors.toSet());

        return RoleResponse.builder()
                .name(role.getName())
                .description(role.getDescription())
                .permission(responses)
                .build();
    }
}
