package com.learnspring.controller;


import com.learnspring.dto.request.ApiResponse;
import com.learnspring.dto.request.PermissionRequest;
import com.learnspring.dto.response.PermissionResponse;
import com.learnspring.model.Permission;
import com.learnspring.service.PermissionService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.util.List;

@RestController
@RequestMapping("/permission")
@RequiredArgsConstructor
public class PermissionController {
    private final PermissionService permissionService ;

    @PostMapping("/create")
    ApiResponse<PermissionResponse> create(@RequestBody PermissionRequest request){
        return ApiResponse.<PermissionResponse>builder()
                .result(permissionService.addPermission(request))
                .build();
    }
    @GetMapping("/getAll")
    ApiResponse<List<PermissionResponse>> getAll(){
        return ApiResponse.<List<PermissionResponse>>builder()
                .result(permissionService.getAll())
                .build();
    }
    @DeleteMapping("/delete/{ID}")
    ApiResponse<Void> delete( @PathVariable String  ID){
        permissionService.delete(ID);
        return ApiResponse.<Void>builder().build();
    }


}
