package com.devland.assignment.assignment12.comment;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devland.assignment.assignment12.comment.dto.CommentRequestDTO;
import com.devland.assignment.assignment12.comment.dto.CommentResponseDTO;
import com.devland.assignment.assignment12.comment.model.Comment;

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
@RequestMapping("/comments")
public class CommentController {
    private final CommentSevice commentSevice;

    @GetMapping
    public ResponseEntity<Page<CommentResponseDTO>> findAll(
            @RequestParam("comment") Optional<String> optionalComment,
            @RequestParam(value = "sort", defaultValue = "ASC") String sortString,
            @RequestParam(value = "order_by", defaultValue = "id") String orderBy,
            @RequestParam(value = "limit", defaultValue = "5") int limit,
            @RequestParam(value = "page", defaultValue = "1") int page) {

        Sort sort = Sort.by(Sort.Direction.valueOf(sortString), orderBy);
        Pageable pageable = PageRequest.of(page - 1, limit, sort);
        Page<Comment> pageComment = this.commentSevice.findAll(optionalComment, pageable);
        Page<CommentResponseDTO> commentResponseDTOs = pageComment.map(Comment::convertToResponse);

        return ResponseEntity.ok(commentResponseDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentResponseDTO> getOne(@PathVariable("id") Long id) {

        Comment existingComment = this.commentSevice.getOne(id);
        CommentResponseDTO commentResponseDTO = existingComment.convertToResponse();

        return ResponseEntity.ok(commentResponseDTO);
    }

    @PostMapping
    public ResponseEntity<CommentResponseDTO> create(@RequestBody @Valid CommentRequestDTO commentRequestDTO) {

        Comment newComment = commentRequestDTO.convertToEntity();
        Comment savedComment = this.commentSevice.create(newComment);
        CommentResponseDTO commentResponseDTO = savedComment.convertToResponse();

        return ResponseEntity.status(HttpStatus.CREATED).body(commentResponseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommentResponseDTO> update(@PathVariable("id") Long id,
            @RequestBody CommentRequestDTO commentRequestDTO) {

        Comment updatedComment = commentRequestDTO.convertToEntity();
        updatedComment.setId(id);
        Comment savedComment = this.commentSevice.update(updatedComment);
        CommentResponseDTO commentResponseDTO = savedComment.convertToResponse();

        return ResponseEntity.ok(commentResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {

        this.commentSevice.delete(id);

        return ResponseEntity.ok().build();
    }

}
