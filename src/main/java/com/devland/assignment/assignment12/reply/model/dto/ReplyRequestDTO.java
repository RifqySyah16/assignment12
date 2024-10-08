package com.devland.assignment.assignment12.reply.model.dto;

import com.devland.assignment.assignment12.applicationuser.model.ApplicationUser;
import com.devland.assignment.assignment12.applicationuser.model.dto.RegisterationRequestDTO;
import com.devland.assignment.assignment12.article.model.Article;
import com.devland.assignment.assignment12.article.model.dto.ArticleRequestDTO;
import com.devland.assignment.assignment12.reply.model.Reply;
import com.devland.assignment.assignment12.usercomment.model.UserComment;
import com.devland.assignment.assignment12.usercomment.model.dto.UserCommentRequestDTO;

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
public class ReplyRequestDTO {
    private Long id;

    @NotBlank(message = "Relies is required")
    private String userReplies;

    @Valid
    private RegisterationRequestDTO registerationRequestDTO;

    @Valid
    private ArticleRequestDTO articleRequestDTO;

    @Valid
    private UserCommentRequestDTO userCommentRequestDTO;

    public Reply convertToEntity() {
        ApplicationUser applicationUser = this.registerationRequestDTO.convertToEntity();
        Article article = this.articleRequestDTO.convertToEntity();
        UserComment userComment = this.userCommentRequestDTO.convertToEntity();

        return Reply.builder()
                .id(this.id)
                .userReplies(this.userReplies)
                .applicationUser(applicationUser)
                .article(article)
                .userComment(userComment)
                .build();
    }
}
