package com.dogiant.cms.dao;

import com.dogiant.cms.domain.todos.User;

public interface UserDao {

	User findUserByOpenId(String openId);

	User save(User user);

}
