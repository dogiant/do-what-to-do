package com.dogiant.cms.dao;

import org.springframework.data.domain.Page;

import com.dogiant.cms.domain.admin.AdminUser;

public interface AdminUserDao{
		
	public boolean checkAdminUser(String userName, String password);

	public AdminUser getAdminUserByUserName(String userName);
	
	public AdminUser getAdminUserByNickname(String nickname);
	
	public boolean modifyAdminUserPassword(String userName, String md5Hex);

	public Page<AdminUser> getAdminUserQueryResult(Integer page,
			Integer size, String beginTime, String endTime, String userName);

	public AdminUser getAdminUserByUserId(Integer userId);

	public void saveAdminUser(AdminUser adminUser);


}
