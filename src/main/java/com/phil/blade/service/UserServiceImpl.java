package com.phil.blade.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.phil.blade.dao.UserDao;
import com.phil.blade.pojo.User;

@Service("bladeUserService")
public class UserServiceImpl implements UserService{
	
	@Autowired
	@Qualifier("bladeUserDao")
	private UserDao userDao;

	@Override
	public User findByEmail(String email) throws Exception {
		return userDao.findByEmail(email);
	}

}
