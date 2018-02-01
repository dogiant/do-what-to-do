package com.dogiant.cms.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.dogiant.cms.domain.admin.AdminUser;

public interface AdminUserRepo extends JpaRepository<AdminUser, Integer>, JpaSpecificationExecutor<AdminUser> {

	@Transactional(readOnly = true)
	@Query("select count(o) from AdminUser o where o.isValid =1 and o.userName =:userName and o.password =:password")
	int checkAdminUser(@Param("userName")String userName, @Param("password")String password);

	@Transactional(readOnly = true)
	@Query("select o from AdminUser o where o.userName =:userName")
	AdminUser getAdminUserByUserName(@Param("userName")String userName);

	@Transactional(readOnly = true)
	@Query("select o from AdminUser o where o.nickname =:nickname")
	AdminUser getAdminUserByNickname(@Param("nickname")String nickname);

}
