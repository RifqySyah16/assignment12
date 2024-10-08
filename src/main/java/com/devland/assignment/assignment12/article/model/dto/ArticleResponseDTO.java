package com.devland.assignment.assignment12.article.model.dto;

import java.sql.Timestamp;

import com.devland.assignment.assignment12.applicationuser.model.dto.RegisterationResponseDTO;

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
public class ArticleResponseDTO {
    private Long id;
    private String content;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private RegisterationResponseDTO registerationResponseDTO;
}
