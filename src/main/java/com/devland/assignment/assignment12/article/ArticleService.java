package com.devland.assignment.assignment12.article;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devland.assignment.assignment12.applicationuser.ApplicationUserService;
import com.devland.assignment.assignment12.applicationuser.model.ApplicationUser;
import com.devland.assignment.assignment12.article.model.Article;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final ApplicationUserService applicationUserService;

    public Page<Article> getAll(Optional<String> optionalContent, Pageable pageable) {
        if (optionalContent.isPresent()) {
            return this.articleRepository.findAllByContentContainsIgnoreCase(optionalContent.get(), pageable);
        }

        return this.articleRepository.findAll(pageable);
    }

    public Article getOne(Long id) {
        return this.articleRepository.findById(id).orElseThrow(() -> new ArticleNotFoundException("Article Not Found"));
    }

    public Article create(Article newArticle) {
        Optional<Article> existingArticle = this.articleRepository.findByName(newArticle.getContent());
        if (existingArticle.isPresent()) {
            throw new ArticleAlreadyExistException("Article Already Exist");
        }

        ApplicationUser existingApplicationUser = this.applicationUserService
                .getOne(newArticle.getApplicationUser().getId());
        newArticle.setApplicationUser(existingApplicationUser);

        return this.articleRepository.save(newArticle);
    }

    public Article update(Article updatedArticle) {
        Article existingArticle = this.getOne(updatedArticle.getId());
        updatedArticle.setId(existingArticle.getId());

        return this.articleRepository.save(updatedArticle);
    }

    public void delete(Long id) {
        Article existingArticle = this.getOne(id);
        this.articleRepository.deleteById(existingArticle.getId());
    }
}
