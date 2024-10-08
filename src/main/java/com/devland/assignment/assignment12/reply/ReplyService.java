package com.devland.assignment.assignment12.reply;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devland.assignment.assignment12.applicationuser.ApplicationUserService;
import com.devland.assignment.assignment12.applicationuser.model.ApplicationUser;
import com.devland.assignment.assignment12.article.ArticleService;
import com.devland.assignment.assignment12.article.model.Article;
import com.devland.assignment.assignment12.reply.model.Reply;
import com.devland.assignment.assignment12.usercomment.UserCommentSevice;
import com.devland.assignment.assignment12.usercomment.model.UserComment;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReplyService {
    private final ReplyRepository replyRepository;
    private final ApplicationUserService applicationUserService;
    private final ArticleService articleService;
    private final UserCommentSevice userCommentSevice;

    public Page<Reply> getAll(Optional<String> optionalReply, Pageable pageable) {
        if (optionalReply.isPresent()) {
            return this.replyRepository.findAllByReplyContainsIgnoreCase(optionalReply.get(), pageable);
        }

        return this.replyRepository.findAll(pageable);
    }

    public Reply getOne(Long id) {
        return this.replyRepository.findById(id).orElseThrow(() -> new ReplyNotFoundException("Reply Not Found"));
    }

    public Reply create(Reply newReply) {
        Optional<Reply> existingReply = this.replyRepository.findByName(newReply.getUserReplies());
        if (existingReply.isPresent()) {
            throw new ReplyAlreadyExistException("Reply Aleady Exist");
        }
        ApplicationUser existingApplicationUser = this.applicationUserService
                .getOne(newReply.getApplicationUser().getId());
        newReply.setApplicationUser(existingApplicationUser);

        Article existingArticle = this.articleService.getOne(newReply.getArticle().getId());
        newReply.setArticle(existingArticle);

        UserComment existingUserComment = this.userCommentSevice.getOne(newReply.getUserComment().getId());
        newReply.setUserComment(existingUserComment);

        return this.replyRepository.save(newReply);
    }

    public Reply update(Reply updatedReply) {
        Reply existingReply = this.getOne(updatedReply.getId());
        updatedReply.setId(existingReply.getId());

        return this.replyRepository.save(updatedReply);
    }

    public void delete(Long id) {
        Reply existingReply = this.getOne(id);
        this.replyRepository.deleteById(existingReply.getId());
    }

}
