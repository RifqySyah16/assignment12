package com.devland.assignment.assignment12.usercomment.model;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.devland.assignment.assignment12.applicationuser.model.ApplicationUser;
import com.devland.assignment.assignment12.applicationuser.model.dto.RegisterationResponseDTO;
import com.devland.assignment.assignment12.article.model.Article;
import com.devland.assignment.assignment12.article.model.dto.ArticleResponseDTO;
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

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String comment;

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private ApplicationUser applicationUser;

    @ManyToOne
    @JoinColumn
    private Article article;

    public UserCommentResponseDTO convertToResponse() {
        RegisterationResponseDTO registerationResponseDTO = this.applicationUser.convertToResponse();
        ArticleResponseDTO articleResponseDTO = this.article.convertToResponse();

        return UserCommentResponseDTO.builder()
                .id(this.id)
                .comment(this.comment)
                .createdAt(this.createdAt)
                .updatedAt(this.updatedAt)
                .registerationResponseDTO(registerationResponseDTO)
                .articleResponseDTO(articleResponseDTO)
                .build();
    }

}
