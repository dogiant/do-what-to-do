package com.dogiant.cms.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.dogiant.cms.domain.admin.Privilege;
import com.dogiant.cms.domain.admin.PrivilegePK;

public interface PrivilegeRepo extends JpaRepository<Privilege, PrivilegePK>, JpaSpecificationExecutor<Privilege> {

}
