package com.wanted.recruitmentannouncement.ApplicationTest;

import com.wanted.recruitmentannouncement.dto.MemberDto;
import com.wanted.recruitmentannouncement.entity.ApplicationRecord;
import com.wanted.recruitmentannouncement.entity.Member;
import com.wanted.recruitmentannouncement.entity.RecruitmentAds;
import com.wanted.recruitmentannouncement.exception.DuplicateException;
import com.wanted.recruitmentannouncement.exception.NotFoundException;
import com.wanted.recruitmentannouncement.repository.ApplicationRecordRepository;
import com.wanted.recruitmentannouncement.repository.MemberRepository;
import com.wanted.recruitmentannouncement.repository.RecruitmentAdsRepository;
import com.wanted.recruitmentannouncement.service.ApplicationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ApplicationServiceTest {
    @Mock
    private RecruitmentAdsRepository recruitmentAdsRepository;

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private ApplicationRecordRepository recordRepository;

    @InjectMocks
    private ApplicationService applicationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("사용자가 없을때 Test")
    void submitApplicationWhenNotFoundTest() {
        MemberDto dto = new MemberDto();
        dto.setUserId("userId");

        when(memberRepository.findByUserId("userId")).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> applicationService.submitApplication(1L, dto));
        verify(memberRepository, times(1)).findByUserId("userId");
    }

    @Test
    @DisplayName("이미 지원한 내역이 있을 경우 Test")
    void submitApplicationWhenApplicationExistsTest() {
        MemberDto dto = new MemberDto();
        dto.setUserId("userId");
        Member member = new Member();
        member.setId(1L);

        when(memberRepository.findByUserId("userId")).thenReturn(Optional.of(member));
        when(recordRepository.existsByMemberId(1L)).thenReturn(true);

        assertThrows(DuplicateException.class, () -> applicationService.submitApplication(1L, dto));
    }

    @Test
    @DisplayName("채용공고가 없을 경우 Test")
    void submitApplicationWhenRecruitmentNotFoundTest() {
        MemberDto dto = new MemberDto();
        dto.setUserId("userId");
        Member member = new Member();
        member.setId(1L);

        when(memberRepository.findByUserId("userId")).thenReturn(Optional.of(member));
        when(recordRepository.existsByMemberId(1L)).thenReturn(false);
        when(recruitmentAdsRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> applicationService.submitApplication(1L, dto));
    }

    @Test
    @DisplayName("채용공고 지원 성공 Test")
    void submitApplicationSuccessTest() {
        MemberDto dto = new MemberDto();
        dto.setUserId("userId");
        Member member = new Member();
        member.setId(1L);
        RecruitmentAds ads = new RecruitmentAds();

        when(memberRepository.findByUserId("userId")).thenReturn(Optional.of(member));
        when(recordRepository.existsByMemberId(1L)).thenReturn(false);
        when(recruitmentAdsRepository.findById(1L)).thenReturn(Optional.of(ads));

        applicationService.submitApplication(1L, dto);

        verify(recordRepository, times(1)).save(any(ApplicationRecord.class));
    }
}
