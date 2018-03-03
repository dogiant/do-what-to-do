package com.dogiant.cms.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.dogiant.cms.domain.todos.UserPlan;

public interface UserPlanRepo extends JpaRepository<UserPlan, Long>, JpaSpecificationExecutor<UserPlan> {

}
