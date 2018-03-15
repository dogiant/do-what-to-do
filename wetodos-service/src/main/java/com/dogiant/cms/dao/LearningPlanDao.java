package com.dogiant.cms.dao;

import com.dogiant.cms.domain.dto.QueryResult;
import com.dogiant.cms.domain.todos.LearningPlan;

public interface LearningPlanDao {

	QueryResult<LearningPlan> getLearningPlanQueryResult(Integer start,
			Integer length, String orderName, String orderDir,
			String searchValue);

	LearningPlan save(LearningPlan learningPlan);

}
