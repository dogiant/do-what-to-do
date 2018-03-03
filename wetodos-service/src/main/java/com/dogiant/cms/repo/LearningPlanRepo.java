package com.dogiant.cms.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.dogiant.cms.domain.todos.LearningPlan;

public interface LearningPlanRepo extends JpaRepository<LearningPlan, Long>, JpaSpecificationExecutor<LearningPlan> {

}
