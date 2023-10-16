package com.wanted.recruitmentannouncement.exception;

import com.wanted.response.ResponseStatus;
import lombok.Getter;

@Getter
public class NotFoundException extends RuntimeException {
    private ResponseStatus status;

    public NotFoundException(ResponseStatus status) {
        super(status.getMessage());
        this.status = status;
    }
}
