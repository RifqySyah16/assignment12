package com.devland.assignment.assignment12.comment;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devland.assignment.assignment12.comment.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    Page<Comment> findAllByCommentContainsIgnoreCase(String comment, Pageable pageable);

    Optional<Comment> findByComment(String userComment);

}
