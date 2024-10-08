package com.devland.assignment.assignment12.article;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devland.assignment.assignment12.article.model.Article;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    Page<Article> findAllByContentContainsIgnoreCase(String content, Pageable pageable);

    Optional<Article> findByName(String content);

}
