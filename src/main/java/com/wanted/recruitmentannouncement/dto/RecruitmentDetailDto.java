package com.wanted.recruitmentannouncement.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RecruitmentDetailDto {
    private String companyId;
    private String jobPosition;
    private Long rewardAmount;
    private String companyName;
    private String technologiesUsed;
    private String jobDescription;
    private String country;
    private String region;
    private List<RecruitmentDto> otherRecruitments;
}
