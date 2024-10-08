package com.devland.assignment.assignment12.usercomment;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devland.assignment.assignment12.usercomment.model.UserComment;
import com.devland.assignment.assignment12.usercomment.model.dto.UserCommentRequestDTO;
import com.devland.assignment.assignment12.usercomment.model.dto.UserCommentResponseDTO;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class UserCommentController {
    private final UserCommentSevice commentSevice;

    @GetMapping
    public ResponseEntity<Page<UserCommentResponseDTO>> findAll(
            @RequestParam("comment") Optional<String> optionalComment,
            @RequestParam(value = "sort", defaultValue = "ASC") String sortString,
            @RequestParam(value = "order_by", defaultValue = "id") String orderBy,
            @RequestParam(value = "limit", defaultValue = "5") int limit,
            @RequestParam(value = "page", defaultValue = "1") int page) {

        Sort sort = Sort.by(Sort.Direction.valueOf(sortString), orderBy);
        Pageable pageable = PageRequest.of(page - 1, limit, sort);
        Page<UserComment> pageComment = this.commentSevice.findAll(optionalComment, pageable);
        Page<UserCommentResponseDTO> commentResponseDTOs = pageComment.map(UserComment::convertToResponse);

        return ResponseEntity.ok(commentResponseDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserCommentResponseDTO> getOne(@PathVariable("id") Long id) {

        UserComment existingComment = this.commentSevice.getOne(id);
        UserCommentResponseDTO commentResponseDTO = existingComment.convertToResponse();

        return ResponseEntity.ok(commentResponseDTO);
    }

    @PostMapping
    public ResponseEntity<UserCommentResponseDTO> create(@RequestBody @Valid UserCommentRequestDTO commentRequestDTO) {

        UserComment newComment = commentRequestDTO.convertToEntity();
        UserComment savedComment = this.commentSevice.create(newComment);
        UserCommentResponseDTO commentResponseDTO = savedComment.convertToResponse();

        return ResponseEntity.status(HttpStatus.CREATED).body(commentResponseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserCommentResponseDTO> update(@PathVariable("id") Long id,
            @RequestBody UserCommentRequestDTO commentRequestDTO) {

        UserComment updatedComment = commentRequestDTO.convertToEntity();
        updatedComment.setId(id);
        UserComment savedComment = this.commentSevice.update(updatedComment);
        UserCommentResponseDTO commentResponseDTO = savedComment.convertToResponse();

        return ResponseEntity.ok(commentResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {

        this.commentSevice.delete(id);

        return ResponseEntity.ok().build();
    }

}
