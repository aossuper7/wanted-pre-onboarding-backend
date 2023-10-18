package com.wanted.recruitmentannouncement.recruitmentTest;

import com.wanted.recruitmentannouncement.dto.RecruitmentDto;
import com.wanted.recruitmentannouncement.entity.RecruitmentAds;
import com.wanted.recruitmentannouncement.repository.RecruitmentAdsRepository;
import com.wanted.recruitmentannouncement.service.RecruitmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

public class RecruitmentSaveTest {

    @InjectMocks
    private RecruitmentService recruitmentService;
    @Mock
    private RecruitmentAdsRepository recruitmentAdsRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("채용공고 저장 Test")
    void SaveRecruitmentTest() throws Exception {
        //Given
        RecruitmentDto dto = new RecruitmentDto();
        dto.setCompanyId("naver11");
        dto.setJobPosition("backend");
        dto.setRewardAmount(1000000L);
        dto.setCompanyName("naver");
        dto.setTechnologiesUsed("spring");
        dto.setJobDescription("우리는 최고의 네이버");
        dto.setCountry("한국");
        dto.setRegion("경기도");

        //When & Then
        RecruitmentAds ads = new RecruitmentAds(dto);

        when(recruitmentAdsRepository.save(any(RecruitmentAds.class))).thenReturn(ads);

        recruitmentService.saveRecruitment(dto);

        Mockito.verify(recruitmentAdsRepository, times(1)).save(any(RecruitmentAds.class));
    }
}
