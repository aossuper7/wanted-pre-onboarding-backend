package com.wanted.recruitmentannouncement.controller;

import com.wanted.recruitmentannouncement.dto.RecruitmentDto;
import com.wanted.recruitmentannouncement.dto.RecruitmentListDto;
import com.wanted.recruitmentannouncement.service.RecruitmentService;
import com.wanted.response.ResponseResult;
import com.wanted.response.ResponseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RecruitmentController {
    private final RecruitmentService recruitmentService;

    /**
     * 채용공고 저장 API
     * @param dto
     * @return
     */
    @PostMapping("/recruitment/s")
    public ResponseResult<ResponseStatus> saveRecruitment(@RequestBody RecruitmentDto dto) {
        recruitmentService.saveRecruitment(dto);
        return new ResponseResult<>(ResponseStatus.SUCCESS);
    }

    /**
     * 채용공고 수정 API
     * @param id
     * @param dto
     * @return
     */
    @PatchMapping("/recruitment/{id}/u")
    public ResponseResult<ResponseStatus> updateRecruitment(@PathVariable long id, @RequestBody RecruitmentDto  dto) {
        recruitmentService.updateRecruitment(id, dto);
        return new ResponseResult<>(ResponseStatus.SUCCESS);
    }

    /**
     * 채용공고 삭제 API
     * @param id
     * @return
     */
    @DeleteMapping("/recruitment/{id}/d")
    public ResponseResult<ResponseStatus> deleteRecruitment(@PathVariable long id) {
        recruitmentService.deleteRecruitment(id);
        return new ResponseResult<>(ResponseStatus.SUCCESS);
    }

    /**
     * 채용공고 목록 가져오기 API능
     * @return
     */
    @GetMapping("/recruitment/all")
    public ResponseResult<List<RecruitmentListDto>> getAllRecruitment() {
        return new ResponseResult<>(recruitmentService.getAllRecruitment());
    }
}
