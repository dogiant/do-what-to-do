package com.dogiant.cms.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.dogiant.cms.domain.admin.Role;

public interface RoleRepo extends JpaRepository<Role, Integer>, JpaSpecificationExecutor<Role> {

}
