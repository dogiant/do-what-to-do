package com.dogiant.cms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dogiant.cms.dao.LearningPlanDao;
import com.dogiant.cms.domain.dto.DataTablesResult;
import com.dogiant.cms.domain.dto.QueryResult;
import com.dogiant.cms.domain.todos.LearningPlan;
import com.dogiant.cms.service.LearningPlanService;

@Service("learningPlanService")
public class LearningPlanServiceImpl implements LearningPlanService {
	
	@Autowired
	private LearningPlanDao leaningPlanDao;

	@Override
	public DataTablesResult<LearningPlan> getLearningPlanDataTablesResult(
			Integer start, Integer length, String orderName, String orderDir,
			String searchValue) {
		QueryResult<LearningPlan> queryResult = leaningPlanDao.getLearningPlanQueryResult(start, length, orderName, orderDir, searchValue);
		
		DataTablesResult<LearningPlan> dataTableResult = new DataTablesResult<LearningPlan>();
		dataTableResult.setData(queryResult.getResult());
		dataTableResult.setRecordsTotal(queryResult.getRecordnum());
		dataTableResult.setRecordsFiltered(queryResult.getRecordnum());
		return dataTableResult;
	}

	@Override
	public LearningPlan save(LearningPlan learningPlan) {
		return leaningPlanDao.save(learningPlan);
		
	}

}
