package com.wanted.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static com.wanted.response.ResponseStatusValue.SUCCESS;

@Getter
@AllArgsConstructor
public class ResponseResult<T> {
    private final boolean issuccess;
    private final String code;
    private final String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T result;

    public ResponseResult(ResponseStatusValue status) {
        this.issuccess = status.isSuccess();
        this.code = status.getCode();
        this.message = status.getMessage();
    }

    public ResponseResult(T result) {
        this.issuccess = SUCCESS.isSuccess();
        this.code = SUCCESS.getCode();
        this.message = SUCCESS.getMessage();
        this.result = result;
    }
}
