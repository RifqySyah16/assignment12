package com.devland.assignment.assignment12.comment;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devland.assignment.assignment12.comment.model.Comment;

public interface CommentRepository extends JpaRepository<Long, Comment> {

}
