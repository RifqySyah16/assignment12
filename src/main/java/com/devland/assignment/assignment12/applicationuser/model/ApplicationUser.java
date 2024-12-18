package com.devland.assignment.assignment12.applicationuser.model;

import java.util.List;

import com.devland.assignment.assignment12.applicationuser.model.dto.RegisterationResponseDTO;
import com.devland.assignment.assignment12.article.model.Article;
import com.devland.assignment.assignment12.reply.model.Reply;
import com.devland.assignment.assignment12.usercomment.model.UserComment;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
public class ApplicationUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String username;

    private String email;

    private String photoPath;

    private String password;

    @OneToMany
    private List<Article> articles;

    @OneToMany
    private List<UserComment> userComments;

    @OneToMany
    private List<Reply> replies;

    public RegisterationResponseDTO convertToResponse() {
        return RegisterationResponseDTO.builder()
                .id(this.id)
                .name(this.name)
                .username(this.username)
                .email(this.email)
                .photoPath(this.photoPath)
                .password(this.password)
                .build();
    }
}
