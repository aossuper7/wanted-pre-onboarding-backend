package com.wanted.recruitmentannouncement.recruitmentTest;

import com.wanted.recruitmentannouncement.dto.RecruitmentDto;
import com.wanted.recruitmentannouncement.entity.RecruitmentAds;
import com.wanted.recruitmentannouncement.exception.NotFoundException;
import com.wanted.recruitmentannouncement.repository.RecruitmentAdsRepository;
import com.wanted.recruitmentannouncement.service.RecruitmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

public class RecruitmentUpdateTest {

    @InjectMocks
    private RecruitmentService recruitmentService;
    @Mock
    private RecruitmentAdsRepository recruitmentAdsRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("채용공고 수정 Test")
    void updateRecruitmentTest() {
        //Given
        RecruitmentAds existAds = new RecruitmentAds();
        RecruitmentDto updateDto = new RecruitmentDto();
        updateDto.setCompanyId("updateCompany");
        updateDto.setJobPosition("updatePosition");

        when(recruitmentAdsRepository.findById(1L)).thenReturn(Optional.of(existAds));

        //When
        recruitmentService.updateRecruitment(1L, updateDto);

        //Then
        verify(recruitmentAdsRepository, times(1)).findById(1L);
        verify(recruitmentAdsRepository, times(1)).save(any(RecruitmentAds.class));
    }

    @Test
    @DisplayName("채용공고 업데이트시 기존 게시글이 없는 경우")
    void updateRecruitmentNotFoundTest() {
        //Given
        RecruitmentDto dto = new RecruitmentDto();
        when(recruitmentAdsRepository.findById(100L)).thenReturn(Optional.empty());

        //Then
        try {
            //When
            recruitmentService.updateRecruitment(100L, dto);
        } catch (Exception e) {
            assert(e instanceof NotFoundException);
        }
    }
}
