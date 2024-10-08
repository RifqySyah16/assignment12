package com.devland.assignment.assignment12.usercomment.model.dto;

import com.devland.assignment.assignment12.applicationuser.model.ApplicationUser;
import com.devland.assignment.assignment12.applicationuser.model.dto.RegisterationRequestDTO;
import com.devland.assignment.assignment12.article.model.Article;
import com.devland.assignment.assignment12.article.model.dto.ArticleRequestDTO;
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
    private RegisterationRequestDTO registerationResponseDTO;

    @Valid
    private ArticleRequestDTO articleRequestDTO;

    public UserComment convertToEntity() {
        ApplicationUser applicationUser = this.registerationResponseDTO.convertToEntity();
        Article article = this.articleRequestDTO.convertToEntity();

        return UserComment.builder()
                .id(this.id)
                .comment(this.comment)
                .applicationUser(applicationUser)
                .article(article)
                .build();
    }
}
