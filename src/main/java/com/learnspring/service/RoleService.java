package com.learnspring.service;


import com.learnspring.dto.request.ApiResponse;
import com.learnspring.dto.request.RoleRequest;
import com.learnspring.dto.response.PermissionResponse;
import com.learnspring.dto.response.RoleResponse;
import com.learnspring.mapper.RoleMapper;
import com.learnspring.model.Permission;
import com.learnspring.model.Role;
import com.learnspring.repository.PermissionRepository;
import com.learnspring.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class RoleService {
     private final RoleRepository roleRepository;
     private final RoleMapper roleMapper ;
     private final PermissionRepository permissionRepository ;
     private final Logger logger  = LoggerFactory.getLogger(RoleService.class);

     public RoleResponse create(RoleRequest request){
          List<Permission> list = permissionRepository.findAllById(request.getPermission()); // find in database first
          logger.info("list permission : {}", list);
          Role newRole = roleMapper.toRoleModel(request, list); // convert dto -> entity
          return roleMapper.toRoleDTO(roleRepository.save(newRole));
     }
     public List<RoleResponse> getAll(){
          List<Role> list = roleRepository.findAll();
          return list.stream().map(role -> roleMapper.toRoleDTO(role)).toList();
     }

}
