package com.wanted.recruitmentannouncement.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecruitmentListDto {
    private Long id;
    private String companyId;
    private String jobPosition;
    private Long rewardAmount;
    private String companyName;
    private String technologiesUsed;
}
