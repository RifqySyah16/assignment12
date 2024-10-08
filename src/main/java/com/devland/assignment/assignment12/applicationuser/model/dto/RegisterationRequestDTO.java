package com.devland.assignment.assignment12.applicationuser.model.dto;

import com.devland.assignment.assignment12.applicationuser.model.ApplicationUser;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterationRequestDTO {
    private Long id;

    @NotBlank(message = "Nmae is required")
    private String name;

    @NotBlank(message = "Username is required")
    private String username;

    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;

    public ApplicationUser convertToEntity() {
        return ApplicationUser.builder()
                .id(this.id)
                .name(this.name)
                .username(this.username)
                .email(this.email)
                .password(this.password)
                .build();
    }
}
