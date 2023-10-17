package com.wanted.recruitmentannouncement.repository;

import com.wanted.recruitmentannouncement.entity.ApplicationRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRecordRepository extends JpaRepository<ApplicationRecord, Long> {

    boolean existsByMemberId(Long memberId);
}
