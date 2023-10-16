package com.wanted.recruitmentannouncement.service;

import com.wanted.recruitmentannouncement.dto.RecruitmentDto;
import com.wanted.recruitmentannouncement.entity.RecruitmentAds;
import com.wanted.recruitmentannouncement.repository.RecruitmentAdsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecruitmentService {
    private final RecruitmentAdsRepository recruitmentAdsRepository;

    public void saveRecruitment(RecruitmentDto dto) {
        RecruitmentAds ads = new RecruitmentAds(dto);
        recruitmentAdsRepository.save(ads);
    }
}
