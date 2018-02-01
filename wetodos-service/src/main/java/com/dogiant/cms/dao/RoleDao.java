package com.dogiant.cms.dao;

import com.dogiant.cms.domain.admin.Role;

public interface RoleDao {
	
	Role getRoleByRoleName(String roleName);
}
