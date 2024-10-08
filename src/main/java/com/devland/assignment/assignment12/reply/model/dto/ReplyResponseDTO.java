package com.devland.assignment.assignment12.reply.model.dto;

import java.sql.Timestamp;

import com.devland.assignment.assignment12.applicationuser.model.dto.RegisterationResponseDTO;
import com.devland.assignment.assignment12.article.model.dto.ArticleResponseDTO;
import com.devland.assignment.assignment12.usercomment.model.dto.UserCommentResponseDTO;

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
public class ReplyResponseDTO {
    private Long id;
    private String userReplies;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private RegisterationResponseDTO registerationResponseDTO;
    private ArticleResponseDTO articleResponseDTO;
    private UserCommentResponseDTO userCommentResponseDTO;
}
