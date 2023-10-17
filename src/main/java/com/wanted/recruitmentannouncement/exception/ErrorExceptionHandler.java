package com.wanted.recruitmentannouncement.exception;

import com.wanted.response.ResponseResult;
import com.wanted.response.ResponseStatusValue;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ResponseResult<ResponseStatusValue> notFoundExceptionHandle(NotFoundException exception) {
        return new ResponseResult<>(exception.getStatus());
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DuplicateException.class)
    public ResponseResult<ResponseStatusValue> duplicationExceptionHandle(DuplicateException exception) {
        return new ResponseResult<>(exception.getStatus());
    }
}
