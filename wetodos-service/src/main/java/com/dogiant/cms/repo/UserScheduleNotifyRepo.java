package com.dogiant.cms.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.dogiant.cms.domain.todos.UserScheduleNotify;

public interface UserScheduleNotifyRepo extends JpaRepository<UserScheduleNotify, Long>, JpaSpecificationExecutor<UserScheduleNotify> {

}
