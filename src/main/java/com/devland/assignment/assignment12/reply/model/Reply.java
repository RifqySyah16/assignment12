package com.devland.assignment.assignment12.reply.model;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.devland.assignment.assignment12.applicationuser.model.ApplicationUser;
import com.devland.assignment.assignment12.applicationuser.model.dto.RegisterationResponseDTO;
import com.devland.assignment.assignment12.article.model.Article;
import com.devland.assignment.assignment12.article.model.dto.ArticleResponseDTO;
import com.devland.assignment.assignment12.reply.model.dto.ReplyResponseDTO;
import com.devland.assignment.assignment12.usercomment.model.UserComment;
import com.devland.assignment.assignment12.usercomment.model.dto.UserCommentResponseDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userReplies;

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private ApplicationUser applicationUser;

    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;

    @ManyToOne
    @JoinColumn(name = "comment_id")
    private UserComment userComment;

    public ReplyResponseDTO convertToResponse() {
        RegisterationResponseDTO registerationResponseDTO = this.applicationUser.convertToResponse();
        ArticleResponseDTO articleResponseDTO = this.article.convertToResponse();
        UserCommentResponseDTO userCommentResponseDTO = this.userComment.convertToResponse();

        return ReplyResponseDTO.builder()
                .id(this.id)
                .userReplies(this.userReplies)
                .createdAt(this.createdAt)
                .updatedAt(this.updatedAt)
                .registerationResponseDTO(registerationResponseDTO)
                .articleResponseDTO(articleResponseDTO)
                .userCommentResponseDTO(userCommentResponseDTO)
                .build();
    }
}
