package com.devland.assignment.assignment12.article.model;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.devland.assignment.assignment12.applicationuser.model.ApplicationUser;
import com.devland.assignment.assignment12.applicationuser.model.dto.RegisterationResponseDTO;
import com.devland.assignment.assignment12.article.model.dto.ArticleResponseDTO;
import com.devland.assignment.assignment12.reply.model.Reply;
import com.devland.assignment.assignment12.usercomment.model.UserComment;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

    @ManyToOne
    private ApplicationUser applicationUser;

    @OneToMany
    private List<UserComment> userComments;

    @OneToMany
    private List<Reply> replies;

    public ArticleResponseDTO convertToResponse() {
        RegisterationResponseDTO registerationResponseDTO = this.applicationUser.convertToResponse();

        return ArticleResponseDTO.builder()
                .id(this.id)
                .content(this.content)
                .createdAt(this.createdAt)
                .updatedAt(this.updatedAt)
                .registerationResponseDTO(registerationResponseDTO)
                .build();
    }
}
