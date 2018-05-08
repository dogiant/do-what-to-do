package com.dogiant.cms.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dogiant.cms.dao.UserDao;
import com.dogiant.cms.domain.todos.User;
import com.dogiant.cms.repo.UserRepo;

@Service("userDao")
public class UserDaoImpl implements UserDao {
	
	@Autowired
	private UserRepo userRepo;

	@Override
	public User findUserByOpenId(String openId) {
		return userRepo.findUserByOpenId(openId);
	}

	@Override
	public User save(User user) {
		return userRepo.save(user);
	}

}
