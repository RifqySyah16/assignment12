package com.devland.assignment.assignment12.comment.dto;

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
public class CommentResponseDTO {
    private Long id;
    private String userComment;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private ApplicationUser applicationUser;
}
