package com.dogiant.cms.service;

import com.dogiant.cms.domain.todos.User;

public interface UserService {

	User findUserByOpenId(String openId);

	User save(User user);

}
