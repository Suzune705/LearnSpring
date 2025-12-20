package com.learnspring.controller;


import com.learnspring.dto.request.ApiResponse;
import com.learnspring.dto.request.RoleRequest;
import com.learnspring.dto.response.RoleResponse;
import com.learnspring.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService ;
    @PostMapping("/create")
    public ApiResponse<RoleResponse> create(@RequestBody RoleRequest request){
        RoleResponse newRole = roleService.create(request);
        return ApiResponse.<RoleResponse>builder()
                .result(newRole)
                .build();
    }
}
