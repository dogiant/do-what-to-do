package com.dogiant.cms.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.dogiant.cms.domain.todos.UserScheduleDetail;

public interface UserScheduleDetailRepo extends JpaRepository<UserScheduleDetail, Long>, JpaSpecificationExecutor<UserScheduleDetail> {

}
