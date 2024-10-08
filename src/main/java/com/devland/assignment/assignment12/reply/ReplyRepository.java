package com.devland.assignment.assignment12.reply;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devland.assignment.assignment12.reply.model.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

    Optional<Reply> findByName(String userReplies);

    Page<Reply> findAllByReplyContainsIgnoreCase(String optionalReply, Pageable pageable);

}
