package com.devland.assignment.assignment12.applicationuser.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterationResponseDTO {
    private Long id;
    private String name;
    private String username;
    private String email;
    private String photoPath;
    private String password;
}
