package com.devland.assignment.assignment12.usercomment;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devland.assignment.assignment12.usercomment.model.UserComment;

public interface UserCommentRepository extends JpaRepository<UserComment, Long> {

    Page<UserComment> findAllByCommentContainsIgnoreCase(String comment, Pageable pageable);

    Optional<UserComment> findByComment(String userComment);

}
