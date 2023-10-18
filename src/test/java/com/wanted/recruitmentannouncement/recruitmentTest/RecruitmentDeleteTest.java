package com.wanted.recruitmentannouncement.recruitmentTest;

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

public class RecruitmentDeleteTest {

    @InjectMocks
    private RecruitmentService recruitmentService;
    @Mock
    private RecruitmentAdsRepository recruitmentAdsRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("채용공고 삭제 Test")
    void deleteRecruitmentTest() {
        //Given
        when(recruitmentAdsRepository.findById(1L)).thenReturn(Optional.of(new RecruitmentAds()));

        //When
        recruitmentService.deleteRecruitment(1L);

        verify(recruitmentAdsRepository, times(1)).findById(1L);
        verify(recruitmentAdsRepository, times(1)).delete(any(RecruitmentAds.class));
    }

    @Test
    @DisplayName("채용공고 삭제시에 글이 없는 경우")
    void deleteRecruitmentNotFoundTest() {
        //Given
        when(recruitmentAdsRepository.findById(100L)).thenReturn(Optional.empty());

        //Then
        try {
            //When
            recruitmentService.deleteRecruitment(100L);
        } catch (Exception e) {
            assert(e instanceof NotFoundException);
        }
    }
}
