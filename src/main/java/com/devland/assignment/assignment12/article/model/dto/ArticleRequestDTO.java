package com.devland.assignment.assignment12.article.model.dto;

import java.util.List;

import com.devland.assignment.assignment12.applicationuser.model.ApplicationUser;
import com.devland.assignment.assignment12.applicationuser.model.dto.RegisterationRequestDTO;
import com.devland.assignment.assignment12.article.model.Article;
import com.devland.assignment.assignment12.reply.model.Reply;
import com.devland.assignment.assignment12.reply.model.dto.ReplyRequestDTO;
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
public class ArticleRequestDTO {
    private Long id;

    @NotBlank(message = "Content is required")
    private String content;

    @Valid
    private RegisterationRequestDTO registerationRequestDTO;

    @Valid
    private List<UserCommentRequestDTO> userCommentsRequestDTOs;

    @Valid
    private List<ReplyRequestDTO> replyRequestDTOs;

    public Article convertToEntity() {
        ApplicationUser applicationUser = this.registerationRequestDTO.convertToEntity();
        List<UserComment> userComments = this.userCommentsRequestDTOs.stream()
                .map(UserCommentRequestDTO::convertToEntity).toList();
        List<Reply> replies = this.replyRequestDTOs.stream().map(ReplyRequestDTO::convertToEntity).toList();

        return Article.builder()
                .id(this.id)
                .content(this.content)
                .applicationUser(applicationUser)
                .userComments(userComments)
                .replies(replies)
                .build();
    }
}
