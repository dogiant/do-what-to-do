package com.dogiant.cms.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.dogiant.cms.domain.todos.User;

public interface UserRepo extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

}
