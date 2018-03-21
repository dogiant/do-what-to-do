package com.dogiant.cms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dogiant.cms.dao.DailyBannerDao;
import com.dogiant.cms.domain.dto.DataTablesResult;
import com.dogiant.cms.domain.dto.QueryResult;
import com.dogiant.cms.domain.todos.DailyBanner;
import com.dogiant.cms.service.DailyBannerService;

@Service("dailyBannerService")
public class DailyBannerServiceImpl implements DailyBannerService {
	
	@Autowired
	private DailyBannerDao dailyBannerDao;

	@Override
	public void addDailyBanner(DailyBanner dailyBanner) {
		dailyBannerDao.addDailyBanner(dailyBanner);
	}

	@Override
	public DailyBanner getDailyBanner(Long id) {
		return dailyBannerDao.getDailyBanner(id);
	}

	@Override
	public void updateDailyBanner(DailyBanner dailyBanner) {
		dailyBannerDao.updateDailyBanner(dailyBanner);
		
	}

	@Override
	public DataTablesResult<DailyBanner> getDailyBannerDataTablesResult(
			Integer start, Integer length, String orderName, String orderDir,
			String searchValue) {
		QueryResult<DailyBanner> queryResult = dailyBannerDao.getDailyBannerQueryResult(start, length, orderName, orderDir, searchValue);
		
		DataTablesResult<DailyBanner> dataTableResult = new DataTablesResult<DailyBanner>();
		dataTableResult.setData(queryResult.getResult());
		dataTableResult.setRecordsTotal(queryResult.getRecordnum());
		dataTableResult.setRecordsFiltered(queryResult.getRecordnum());
		return dataTableResult;
	}

}
