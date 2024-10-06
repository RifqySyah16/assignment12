package com.devland.assignment.assignment12.usercomment;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devland.assignment.assignment12.applicationuser.ApplicationUserService;
import com.devland.assignment.assignment12.applicationuser.model.ApplicationUser;
import com.devland.assignment.assignment12.usercomment.model.UserComment;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserCommentSevice {
    private final UserCommentRepository commentRepository;
    private final ApplicationUserService applicationUserService;

    public Page<UserComment> findAll(Optional<String> optionalComment, Pageable pageable) {
        if (optionalComment.isPresent()) {
            return this.commentRepository.findAllByCommentContainsIgnoreCase(optionalComment.get(), pageable);
        }

        return this.commentRepository.findAll(pageable);
    }

    public UserComment getOne(Long id) {
        return this.commentRepository.findById(id)
                .orElseThrow(() -> new UserCommentNotFoundException("Comment Not Found"));
    }

    public UserComment create(UserComment newComment) {
        Optional<UserComment> existingComment = this.commentRepository.findByComment(newComment.getComment());
        if (existingComment.isPresent()) {
            throw new UserCommentAlreadyExistException("Comment Already Exist");
        }

        ApplicationUser existingUser = this.applicationUserService.getOne(newComment.getApplicationUser().getId());
        newComment.setApplicationUser(existingUser);

        return this.commentRepository.save(newComment);
    }

    public UserComment update(UserComment updatedComment) {
        UserComment existingComment = this.getOne(updatedComment.getId());
        updatedComment.setId(existingComment.getId());

        return this.commentRepository.save(updatedComment);
    }

    public void delete(Long id) {
        UserComment existingComment = this.getOne(id);
        this.commentRepository.deleteById(existingComment.getId());
    }
}
