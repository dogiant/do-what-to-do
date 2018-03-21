package com.dogiant.cms.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.dogiant.cms.domain.todos.DailyBanner;

public interface DailyBannerRepo extends JpaRepository<DailyBanner, Long>, JpaSpecificationExecutor<DailyBanner> {

	@Transactional(readOnly = true)
	@Query("select o from DailyBanner o where o.date =:date and o.status>=0 order by o.id desc")
	List<DailyBanner> getDailyBannerByDate(@Param("date")String date);

}
