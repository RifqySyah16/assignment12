package com.devland.assignment.assignment12.comment;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devland.assignment.assignment12.applicationuser.ApplicationUserService;
import com.devland.assignment.assignment12.applicationuser.model.ApplicationUser;
import com.devland.assignment.assignment12.comment.model.Comment;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentSevice {
    private final CommentRepository commentRepository;
    private final ApplicationUserService applicationUserService;

    public Page<Comment> findAll(Optional<String> optionalComment, Pageable pageable) {
        if (optionalComment.isPresent()) {
            return this.commentRepository.findAllByCommentContainsIgnoreCase(optionalComment.get(), pageable);
        }

        return this.commentRepository.findAll(pageable);
    }

    public Comment getOne(Long id) {
        return this.commentRepository.findById(id).orElseThrow(() -> new CommentNotFoundException("Comment Not Found"));
    }

    public Comment create(Comment newComment) {
        Optional<Comment> existingComment = this.commentRepository.findByComment(newComment.getUserComment());
        if (existingComment.isPresent()) {
            throw new CommentAlreadyExistException("Comment Already Exist");
        }

        ApplicationUser existingUser = this.applicationUserService.getOne(newComment.getApplicationUser().getId());
        newComment.setApplicationUser(existingUser);

        return this.commentRepository.save(newComment);
    }

    public Comment update(Comment updatedComment) {
        Comment existingComment = this.getOne(updatedComment.getId());
        updatedComment.setId(existingComment.getId());

        return this.commentRepository.save(updatedComment);
    }

    public void delete(Long id) {
        Comment existingComment = this.getOne(id);
        this.commentRepository.deleteById(existingComment.getId());
    }
}
