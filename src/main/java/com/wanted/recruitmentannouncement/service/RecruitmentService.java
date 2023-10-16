package com.wanted.recruitmentannouncement.service;

import com.wanted.recruitmentannouncement.dto.RecruitmentDto;
import com.wanted.recruitmentannouncement.dto.RecruitmentListDto;
import com.wanted.recruitmentannouncement.entity.RecruitmentAds;
import com.wanted.recruitmentannouncement.exception.NotFoundException;
import com.wanted.recruitmentannouncement.repository.RecruitmentAdsRepository;
import com.wanted.response.ResponseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecruitmentService {
    private final RecruitmentAdsRepository recruitmentAdsRepository;

    /**
     * 채용공고 save Service
     * @param dto
     */
    public void saveRecruitment(RecruitmentDto dto) {
        RecruitmentAds ads = new RecruitmentAds(dto);
        recruitmentAdsRepository.save(ads);
    }

    /**
     * 채용공고 update Service
     * @param id
     * @param dto
     */
    public void updateRecruitment(long id, RecruitmentDto dto) {
        RecruitmentAds ads = recruitmentAdsRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ResponseStatus.NOT_FOUND_RECRUITMENT));

        toRecruitmentAds(ads, dto);
        recruitmentAdsRepository.save(ads);
    }

    private void toRecruitmentAds(RecruitmentAds ads, RecruitmentDto dto) {
        ads.setCompanyId(dto.getCompanyId());
        ads.setJobPosition(dto.getJobPosition());
        ads.setRewardAmount(dto.getRewardAmount());
        ads.setCompanyName(dto.getCompanyName());
        ads.setTechnologiesUsed(dto.getTechnologiesUsed());
        ads.setJobDescription(dto.getJobDescription());
        ads.setCountry(dto.getCountry());
        ads.setRegion(dto.getRegion());
    }

    /**
     * 채용공고 삭제 Service
     * @param id
     */
    public void deleteRecruitment(long id) {
        RecruitmentAds ads = recruitmentAdsRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ResponseStatus.NOT_FOUND_RECRUITMENT));
        recruitmentAdsRepository.delete(ads);
    }

    /**
     * 채용공고 목록 가져오기 Service
     * @return
     */
    public List<RecruitmentListDto> getAllRecruitment() {
        List<RecruitmentAds> adsList = recruitmentAdsRepository.findAll();
        return adsList.stream()
                .map(this::convertToRecruitmentDto)
                .collect(Collectors.toList());
    }

    private RecruitmentListDto convertToRecruitmentDto(RecruitmentAds ads) {
        RecruitmentListDto dto = new RecruitmentListDto();
        dto.setId(ads.getId());
        dto.setCompanyId(ads.getCompanyId());
        dto.setJobPosition(ads.getJobPosition());
        dto.setRewardAmount(ads.getRewardAmount());
        dto.setCompanyName(ads.getCompanyName());
        dto.setTechnologiesUsed(ads.getTechnologiesUsed());
        return dto;
    }

    /**
     * 채용공고 검색 Service
     * @param keyword
     * @return
     */
    public List<RecruitmentListDto> searchRecruitment(String keyword) {
        List<RecruitmentAds> adsList = recruitmentAdsRepository.searchByKeyword(keyword);
        return adsList.stream()
                .map(this::convertToRecruitmentDto)
                .collect(Collectors.toList());
    }
}
