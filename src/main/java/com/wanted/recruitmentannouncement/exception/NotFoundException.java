package com.wanted.recruitmentannouncement.exception;

import com.wanted.response.ResponseStatusValue;
import lombok.Getter;

@Getter
public class NotFoundException extends RuntimeException {
    private ResponseStatusValue status;

    public NotFoundException(ResponseStatusValue status) {
        super(status.getMessage());
        this.status = status;
    }
}
