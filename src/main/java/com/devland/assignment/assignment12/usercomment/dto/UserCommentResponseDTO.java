package com.devland.assignment.assignment12.usercomment.dto;

import java.sql.Timestamp;

import com.devland.assignment.assignment12.applicationuser.model.ApplicationUser;

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
public class UserCommentResponseDTO {
    private Long id;
    private String comment;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private ApplicationUser applicationUser;
}
