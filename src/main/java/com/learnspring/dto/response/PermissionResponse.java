package com.learnspring.dto.response;


import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PermissionResponse {
    private String name ;
    private String description;

}
