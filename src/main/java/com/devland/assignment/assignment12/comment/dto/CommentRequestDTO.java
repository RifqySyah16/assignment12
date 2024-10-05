package com.devland.assignment.assignment12.comment.dto;

import com.devland.assignment.assignment12.applicationuser.model.ApplicationUser;
import com.devland.assignment.assignment12.comment.model.Comment;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentRequestDTO {
    private Long id;

    @NotBlank(message = "Comment is Required")
    private String userComment;

    @Valid
    private ApplicationUser applicationUser;

    public Comment convertToEntity() {
        return Comment.builder()
        .id(this.id)
        .userComment(this.userComment)
        .applicationUser(applicationUser)
        .build();
    }
}
