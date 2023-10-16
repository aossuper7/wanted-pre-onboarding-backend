package com.wanted.response;

import lombok.Getter;

@Getter
public enum ResponseStatus {
    /**
     * 200 : 요청 성공
     */
    SUCCESS(true, "200", "요청에 성공하였습니다."),
    /**
     * 400 : Request, Response 오류
     */
    NOT_FOUND_RECRUITMENT(false, "404", "채용 공고를 찾을 수 없습니다.");

    private final boolean isSuccess;
    private final String code;
    private final String message;

    ResponseStatus(boolean isSuccess, String code, String message) {
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }
}
