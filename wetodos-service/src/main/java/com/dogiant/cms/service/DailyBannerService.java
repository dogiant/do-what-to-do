package com.dogiant.cms.service;

import com.dogiant.cms.domain.dto.DataTablesResult;
import com.dogiant.cms.domain.todos.DailyBanner;

public interface DailyBannerService {

	void addDailyBanner(DailyBanner dailyBanner);

	DailyBanner getDailyBanner(Long id);

	void updateDailyBanner(DailyBanner dailyBanner);

	DataTablesResult<DailyBanner> getDailyBannerDataTablesResult(Integer start,
			Integer length, String orderName, String orderDir,
			String searchValue);

}
