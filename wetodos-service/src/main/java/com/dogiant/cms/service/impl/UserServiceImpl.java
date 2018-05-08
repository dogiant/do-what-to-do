package com.dogiant.cms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dogiant.cms.dao.UserDao;
import com.dogiant.cms.domain.todos.User;
import com.dogiant.cms.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;

	@Override
	public User findUserByOpenId(String openId) {
		return userDao.findUserByOpenId(openId);
	}

	@Override
	public User save(User user) {
		return userDao.save(user);
	}

}
