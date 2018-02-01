package com.dogiant.cms.service;

import com.dogiant.cms.domain.admin.AdminUser;
import com.dogiant.cms.domain.dto.PagedResult;

public interface AdminUserService {

	AdminUser findUserByUserNameValid(String userName);
	
	AdminUser findUserByNicknameValid(String userName);

	void saveOrUpdate(AdminUser adminUser);

	AdminUser getAdminUserByUserId(Integer userId);

	PagedResult<AdminUser> getAdminUserQueryResult(Integer pageNo, Integer pageRows, String beginTime, String endTime,
			String userName);

}