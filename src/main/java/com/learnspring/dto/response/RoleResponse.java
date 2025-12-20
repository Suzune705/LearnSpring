package com.learnspring.dto.response;


import lombok.*;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class RoleResponse {
    private String name ;
    private String description ;
    private Set<PermissionResponse> permission ;

}
