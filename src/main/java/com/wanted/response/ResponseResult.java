package com.wanted.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResponseResult {
    private final boolean issuccess;
    private final String code;
    private final String message;

    public ResponseResult(ResponseStatus status) {
        this.issuccess = status.isSuccess();
        this.code = status.getCode();
        this.message = status.getMessage();
    }
}
