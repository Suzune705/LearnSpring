package com.learnspring.dto.request;


import lombok.*;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class RoleRequest {
    private String name ;
    private String description;
    private Set<String> permission ;
}
