package com.devland.assignment.assignment12.article;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devland.assignment.assignment12.article.model.Article;
import com.devland.assignment.assignment12.article.model.dto.ArticleRequestDTO;
import com.devland.assignment.assignment12.article.model.dto.ArticleResponseDTO;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequiredArgsConstructor
@RequestMapping("/articles")
public class ArticleController {
    private final ArticleService articleService;

    @GetMapping
    public ResponseEntity<Page<ArticleResponseDTO>> getAll(
            @RequestParam("content") Optional<String> optionalContent,
            @RequestParam(value = "sort", defaultValue = "ASC") String sortString,
            @RequestParam(value = "order_by", defaultValue = "id") String orderBy,
            @RequestParam(value = "limit", defaultValue = "5") int limit,
            @RequestParam(value = "page", defaultValue = "1") int page) {

        Sort sort = Sort.by(Sort.Direction.valueOf(sortString), "id");
        Pageable pageable = PageRequest.of(page - 1, limit, sort);
        Page<Article> pageArticle = this.articleService.getAll(optionalContent, pageable);
        Page<ArticleResponseDTO> articleResponseDTOs = pageArticle.map(Article::convertToResponse);

        return ResponseEntity.ok(articleResponseDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArticleResponseDTO> getOne(@PathVariable("id") Long id) {
        Article existingArticle = this.articleService.getOne(id);
        ArticleResponseDTO articleResponseDTO = existingArticle.convertToResponse();

        return ResponseEntity.ok(articleResponseDTO);
    }

    @PostMapping
    public ResponseEntity<ArticleResponseDTO> create(@RequestBody @Valid ArticleRequestDTO articleRequestDTO) {
        Article newArticle = articleRequestDTO.convertToEntity();

        Article savedArticle = this.articleService.create(newArticle);
        ArticleResponseDTO articleResponseDTO = savedArticle.convertToResponse();

        return ResponseEntity.status(HttpStatus.CREATED).body(articleResponseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArticleResponseDTO> update(@PathVariable("id") Long id,
            @RequestBody ArticleRequestDTO articleRequestDTO) {
        Article updatedArticle = articleRequestDTO.convertToEntity();
        updatedArticle.setId(id);
        Article savedArticle = this.articleService.update(updatedArticle);
        ArticleResponseDTO articleResponseDTO = savedArticle.convertToResponse();

        return ResponseEntity.ok(articleResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        this.articleService.delete(id);

        return ResponseEntity.ok().build();
    }

}
