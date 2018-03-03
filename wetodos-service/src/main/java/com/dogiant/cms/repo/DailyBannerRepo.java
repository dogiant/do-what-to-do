package com.dogiant.cms.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.dogiant.cms.domain.todos.DailyBanner;

public interface DailyBannerRepo extends JpaRepository<DailyBanner, Long>, JpaSpecificationExecutor<DailyBanner> {

}
