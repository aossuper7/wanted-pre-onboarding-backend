package com.wanted.recruitmentannouncement.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecruitmentDto {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long id;
    private String companyId;
    private String jobPosition;
    private Long rewardAmount;
    private String companyName;
    private String technologiesUsed;
    private String jobDescription;
    private String country;
    private String region;
}
