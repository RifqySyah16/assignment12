package com.devland.assignment.assignment12.comment;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class CommentAlreadyExistException extends RuntimeException {

    public CommentAlreadyExistException(String message) {
        super(message);
    }
}
