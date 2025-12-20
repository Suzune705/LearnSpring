package com.learnspring.dto.request;


import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PermissionRequest {
    private String name;
    private String description;
}
