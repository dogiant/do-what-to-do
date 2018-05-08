package com.dogiant.cms.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.dogiant.cms.domain.todos.User;

public interface UserRepo extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

	@Transactional(readOnly = true)
	@Query("select o from User o where o.openId =:openId and o.status>=0")
	User findUserByOpenId(@Param("openId")String openId);

}
