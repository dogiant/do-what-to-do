package com.dogiant.cms.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.dogiant.cms.domain.website.SectionHistory;

public interface SectionHistoryRepo
		extends JpaRepository<SectionHistory, Long>, JpaSpecificationExecutor<SectionHistory> {

}
