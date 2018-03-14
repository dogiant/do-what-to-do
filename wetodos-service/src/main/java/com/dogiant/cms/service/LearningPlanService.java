package com.dogiant.cms.service;

import com.dogiant.cms.domain.dto.DataTablesResult;
import com.dogiant.cms.domain.todos.LearningPlan;

public interface LearningPlanService {

	DataTablesResult<LearningPlan> getLearningPlanDataTablesResult(
			Integer start, Integer length, String orderName, String orderDir,
			String searchValue);

}
