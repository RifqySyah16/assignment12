package com.devland.assignment.assignment12.usercomment;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class UserCommentNotFoundException extends RuntimeException {

    public UserCommentNotFoundException(String message) {
        super(message);
    }
}
