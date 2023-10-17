package com.wanted.recruitmentannouncement.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Entity
@NoArgsConstructor
public class ApplicationRecord {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "recruitment_ads")
    private RecruitmentAds recruitmentAds;

    public ApplicationRecord(Member member, RecruitmentAds ads) {
        this.member = member;
        this.recruitmentAds = ads;
    }

}
