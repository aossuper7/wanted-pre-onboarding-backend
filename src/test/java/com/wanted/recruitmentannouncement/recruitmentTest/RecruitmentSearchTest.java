package com.wanted.recruitmentannouncement.recruitmentTest;

import com.wanted.recruitmentannouncement.dto.RecruitmentListDto;
import com.wanted.recruitmentannouncement.entity.RecruitmentAds;
import com.wanted.recruitmentannouncement.repository.RecruitmentAdsRepository;
import com.wanted.recruitmentannouncement.service.RecruitmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class RecruitmentSearchTest {

    @InjectMocks
    private RecruitmentService recruitmentService;
    @Mock
    private RecruitmentAdsRepository recruitmentAdsRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("검색어가 없는 상황")
    void searchRecruitmentWithNoMatchTest() {
        // Given
        String keyword = "developer";
        when(recruitmentAdsRepository.searchByKeyword(keyword)).thenReturn(List.of());

        // When
        List<RecruitmentListDto> result = recruitmentService.searchRecruitment(keyword);

        // Then
        assertEquals(0, result.size());
        verify(recruitmentAdsRepository, times(1)).searchByKeyword(keyword);
    }

    @Test
    @DisplayName("검색어가 있는 상황")
    void searchRecruitmentWithMatchTest() {
        // Given
        String keyword = "developer";
        RecruitmentAds ad1 = new RecruitmentAds();
        ad1.setCompanyId("company1");
        RecruitmentAds ad2 = new RecruitmentAds();
        ad2.setCompanyId("company2");
        when(recruitmentAdsRepository.searchByKeyword(keyword)).thenReturn(Arrays.asList(ad1, ad2));

        // When
        List<RecruitmentListDto> result = recruitmentService.searchRecruitment(keyword);

        // Then
        assertEquals(2, result.size());
        assertEquals("company1", result.get(0).getCompanyId());
        assertEquals("company2", result.get(1).getCompanyId());
        verify(recruitmentAdsRepository, times(1)).searchByKeyword(keyword);
    }
}
