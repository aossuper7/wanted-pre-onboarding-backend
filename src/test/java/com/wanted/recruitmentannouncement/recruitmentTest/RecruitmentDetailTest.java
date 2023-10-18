package com.wanted.recruitmentannouncement.recruitmentTest;

import com.wanted.recruitmentannouncement.dto.RecruitmentDetailDto;
import com.wanted.recruitmentannouncement.entity.RecruitmentAds;
import com.wanted.recruitmentannouncement.exception.NotFoundException;
import com.wanted.recruitmentannouncement.repository.RecruitmentAdsRepository;
import com.wanted.recruitmentannouncement.service.RecruitmentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class RecruitmentDetailTest {

    @InjectMocks
    private RecruitmentService recruitmentService;
    @Mock
    private RecruitmentAdsRepository recruitmentAdsRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("채용공고가 없을 경우")
    void getDetailWithNonExist() {
        // Given
        when(recruitmentAdsRepository.findById(1L)).thenReturn(Optional.empty());

        // Then
        Assertions.assertThrows(NotFoundException.class, () -> {
            // When
            recruitmentService.getRecruitmentDetail(1L);
        });
        verify(recruitmentAdsRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("채용 공고가 있을 경우")
    void getDetailWithExist() {
        // Given
        RecruitmentAds mainAds = new RecruitmentAds();
        mainAds.setCompanyId("company1");
        RecruitmentAds otherAds = new RecruitmentAds();
        otherAds.setCompanyId("company1");

        when(recruitmentAdsRepository.findById(1L)).thenReturn(Optional.of(mainAds));
        when(recruitmentAdsRepository.findByCompanyIdAndIdNot("company1", 1L)).thenReturn(List.of(otherAds));

        // When
        RecruitmentDetailDto result = recruitmentService.getRecruitmentDetail(1L);

        // Then
        assertEquals("company1", result.getCompanyId());
        assertEquals(1, result.getOtherRecruitments().size());
        verify(recruitmentAdsRepository, times(1)).findById(1L);
        verify(recruitmentAdsRepository, times(1)).findByCompanyIdAndIdNot("company1", 1L);
    }
}
