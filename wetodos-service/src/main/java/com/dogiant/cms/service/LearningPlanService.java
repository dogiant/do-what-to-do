package com.dogiant.cms.service;

import com.dogiant.cms.domain.dto.DataTablesResult;
import com.dogiant.cms.domain.todos.LearningPlan;

public interface LearningPlanService {

	DataTablesResult<LearningPlan> getLearningPlanDataTablesResult(
			Integer start, Integer length, String orderName, String orderDir,
			String searchValue);

	LearningPlan save(LearningPlan learningPlan);

	LearningPlan findLearningPlanById(Long id);

	DataTablesResult<LearningPlan> getLearningPlanDataTablesResultSimple(
			Integer start, Integer length, String keyword);


}
