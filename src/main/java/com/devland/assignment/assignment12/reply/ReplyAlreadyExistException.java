package com.devland.assignment.assignment12.reply;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class ReplyAlreadyExistException extends RuntimeException {

    public ReplyAlreadyExistException(String message) {
        super(message);
    }
}
