package com.devland.assignment.assignment12.usercomment;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class UserCommentAlreadyExistException extends RuntimeException {

    public UserCommentAlreadyExistException(String message) {
        super(message);
    }
}
