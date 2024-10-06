package com.devland.assignment.assignment12.usercomment.dto;

import com.devland.assignment.assignment12.applicationuser.model.ApplicationUser;
import com.devland.assignment.assignment12.usercomment.model.UserComment;

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
public class UserCommentRequestDTO {
    private Long id;

    @NotBlank(message = "Comment is Required")
    private String comment;

    @Valid
    private ApplicationUser applicationUser;

    public UserComment convertToEntity() {
        return UserComment.builder()
                .id(this.id)
                .comment(this.comment)
                .applicationUser(applicationUser)
                .build();
    }
}
