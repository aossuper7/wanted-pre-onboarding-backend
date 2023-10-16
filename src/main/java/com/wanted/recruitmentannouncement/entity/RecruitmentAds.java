package com.wanted.recruitmentannouncement.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class RecruitmentAds {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
