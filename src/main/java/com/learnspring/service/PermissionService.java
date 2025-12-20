package com.learnspring.service;


import com.learnspring.dto.request.PermissionRequest;
import com.learnspring.dto.response.PermissionResponse;
import com.learnspring.mapper.PermissionMapper;
import com.learnspring.model.Permission;
import com.learnspring.repository.PermissionRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class PermissionService {

    private final PermissionRepository permissionRepo ;
    private final PermissionMapper permissionMapper ;

    public PermissionResponse addPermission(PermissionRequest request){
        Permission permission = permissionMapper.toPermission(request);
        permission =  permissionRepo.save(permission);
        return permissionMapper.toPermissionResponse(permission);
    }

    public List<PermissionResponse> getAll(){
        List<Permission> list = permissionRepo.findAll();
        List<PermissionResponse> permissionResponses = list.stream()
                .map(permission -> permissionMapper.toPermissionResponse(permission))
                .toList();
        return  permissionResponses;
    }

    public void delete(String permission){
        permissionRepo.deleteById(permission);
    }
}
