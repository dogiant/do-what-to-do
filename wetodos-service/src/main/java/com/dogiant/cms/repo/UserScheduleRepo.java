package com.dogiant.cms.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.dogiant.cms.domain.todos.UserSchedule;

public interface UserScheduleRepo extends JpaRepository<UserSchedule, Long>, JpaSpecificationExecutor<UserSchedule> {

}
