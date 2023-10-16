package com.wanted.recruitmentannouncement.repository;

import com.wanted.recruitmentannouncement.entity.RecruitmentAds;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RecruitmentAdsRepository extends JpaRepository<RecruitmentAds, Long> {

    @Query("select r from RecruitmentAds r where " +
            "r.companyName like %:keyword% or " +
            "r.jobPosition like %:keyword% or " +
            "r.technologiesUsed like %:keyword%")
    List<RecruitmentAds> searchByKeyword(@Param("keyword") String keyword);

    List<RecruitmentAds> findByCompanyIdAndIdNot(String companyId, Long id);
}
