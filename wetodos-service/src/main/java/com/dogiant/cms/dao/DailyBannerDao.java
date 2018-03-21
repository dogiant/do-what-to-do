package com.dogiant.cms.dao;

import com.dogiant.cms.domain.dto.QueryResult;
import com.dogiant.cms.domain.todos.DailyBanner;

public interface DailyBannerDao {

	void addDailyBanner(DailyBanner dailyBanner);

	DailyBanner getDailyBanner(Long id);

	void updateDailyBanner(DailyBanner dailyBanner);

	QueryResult<DailyBanner> getDailyBannerQueryResult(Integer start,
			Integer length, String orderName, String orderDir,
			String searchValue);

	DailyBanner getDailyBannerByDate(String date);


}