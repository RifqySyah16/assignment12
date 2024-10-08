package com.devland.assignment.assignment12.article;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class ArticleAlreadyExistException extends RuntimeException{

    public ArticleAlreadyExistException(String message) {
        super(message);
    }
}
