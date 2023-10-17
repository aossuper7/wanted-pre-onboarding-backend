package com.wanted.recruitmentannouncement.exception;

import com.wanted.response.ResponseStatusValue;
import lombok.Getter;

@Getter
public class DuplicateException extends RuntimeException {
    private ResponseStatusValue status;

    public DuplicateException(ResponseStatusValue status) {
        super(status.getMessage());
        this.status = status;
    }
}
