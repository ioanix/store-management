package com.ioana.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ProjectException extends RuntimeException {

    public ProjectException(String message) {

        super(message);
    }
}
