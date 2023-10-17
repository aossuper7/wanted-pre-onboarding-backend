package com.wanted.recruitmentannouncement.controller;

import com.wanted.recruitmentannouncement.dto.RecruitmentDetailDto;
import com.wanted.recruitmentannouncement.dto.RecruitmentDto;
import com.wanted.recruitmentannouncement.dto.RecruitmentListDto;
import com.wanted.recruitmentannouncement.service.RecruitmentService;
import com.wanted.response.ResponseResult;
import com.wanted.response.ResponseStatusValue;
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
    public ResponseResult<ResponseStatusValue> saveRecruitment(@RequestBody RecruitmentDto dto) {
        recruitmentService.saveRecruitment(dto);
        return new ResponseResult<>(ResponseStatusValue.SUCCESS);
    }

    /**
     * 채용공고 수정 API
     * @param id
     * @param dto
     * @return
     */
    @PatchMapping("/recruitment/{id}/u")
    public ResponseResult<ResponseStatusValue> updateRecruitment(@PathVariable long id, @RequestBody RecruitmentDto  dto) {
        recruitmentService.updateRecruitment(id, dto);
        return new ResponseResult<>(ResponseStatusValue.SUCCESS);
    }

    /**
     * 채용공고 삭제 API
     * @param id
     * @return
     */
    @DeleteMapping("/recruitment/{id}/d")
    public ResponseResult<ResponseStatusValue> deleteRecruitment(@PathVariable long id) {
        recruitmentService.deleteRecruitment(id);
        return new ResponseResult<>(ResponseStatusValue.SUCCESS);
    }

    /**
     * 채용공고 목록 가져오기 API
     * @return
     */
    @GetMapping("/recruitment/all")
    public ResponseResult<List<RecruitmentListDto>> getAllRecruitment() {
        return new ResponseResult<>(recruitmentService.getAllRecruitment());
    }

    /**
     * 채용공고 검색 API
     * @param keyword
     * @return
     */
    @GetMapping("/recruitment/search")
    public ResponseResult<List<RecruitmentListDto>> searchRecruitment(String keyword) {
        return new ResponseResult<>(recruitmentService.searchRecruitment(keyword));
    }

    /**
     * 채용공고 상세페이지 API
     * @param id
     * @return
     */
    @GetMapping("/recruitment/{id}/detail")
    public ResponseResult<RecruitmentDetailDto> getRecruitmentDetail(@PathVariable long id) {
        return new ResponseResult<>(recruitmentService.getRecruitmentDetail(id));
    }
}
