package com.wanted.recruitmentannouncement.controller;

import com.wanted.recruitmentannouncement.dto.MemberDto;
import com.wanted.recruitmentannouncement.service.ApplicationService;
import com.wanted.response.ResponseResult;
import com.wanted.response.ResponseStatusValue;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationService applicationService;

    @PostMapping("/applicants/{recruitmentId}")
    public ResponseResult<ResponseStatusValue> applicants(@PathVariable long recruitmentId, @RequestBody MemberDto memberDto) {
        applicationService.submitApplication(recruitmentId, memberDto);
        return new ResponseResult<>(ResponseStatusValue.SUCCESS);
    }
}
