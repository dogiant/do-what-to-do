package com.dogiant.cms.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.dogiant.cms.domain.admin.SystemModel;

public interface SystemModelRepo extends JpaRepository<SystemModel, String>, JpaSpecificationExecutor<SystemModel> {

}
