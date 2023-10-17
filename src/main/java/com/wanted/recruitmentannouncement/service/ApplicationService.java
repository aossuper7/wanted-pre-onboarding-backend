package com.wanted.recruitmentannouncement.service;

import com.wanted.recruitmentannouncement.dto.MemberDto;
import com.wanted.recruitmentannouncement.entity.ApplicationRecord;
import com.wanted.recruitmentannouncement.entity.Member;
import com.wanted.recruitmentannouncement.entity.RecruitmentAds;
import com.wanted.recruitmentannouncement.exception.DuplicateException;
import com.wanted.recruitmentannouncement.exception.NotFoundException;
import com.wanted.recruitmentannouncement.repository.ApplicationRecordRepository;
import com.wanted.recruitmentannouncement.repository.MemberRepository;
import com.wanted.recruitmentannouncement.repository.RecruitmentAdsRepository;
import com.wanted.response.ResponseStatusValue;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApplicationService {

    private final RecruitmentAdsRepository recruitmentAdsRepository;
    private final MemberRepository memberRepository;
    private final ApplicationRecordRepository recordRepository;

    public void submitApplication(long recruitmentId, MemberDto memberDto) {
        Member member = memberRepository.findByUserId(memberDto.getUserId())
                .orElseThrow(() -> new NotFoundException(ResponseStatusValue.NOT_FOUND_MEMBER));

        if (recordRepository.existsByMemberId(member.getId()))
            throw new DuplicateException(ResponseStatusValue.DUPLICATE_APPLICATION);

        RecruitmentAds ads = recruitmentAdsRepository.findById(recruitmentId)
                .orElseThrow(() -> new NotFoundException(ResponseStatusValue.NOT_FOUND_RECRUITMENT));

        ApplicationRecord record = new ApplicationRecord(member, ads);
        recordRepository.save(record);
    }
}
