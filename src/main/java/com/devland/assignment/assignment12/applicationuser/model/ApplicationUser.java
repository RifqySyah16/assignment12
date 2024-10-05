package com.devland.assignment.assignment12.applicationuser.model;

import com.devland.assignment.assignment12.applicationuser.dto.RegisterationResponseDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String username;

    private String email;
    
    private String password;

    public RegisterationResponseDTO convertToResponse() {
        return RegisterationResponseDTO.builder()
        .id(this.id)
        .name(this.name)
        .username(this.username)
        .email(this.email)
        .password(this.password)
        .build();
    }
}
