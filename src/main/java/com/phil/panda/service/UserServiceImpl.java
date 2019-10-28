package com.phil.panda.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.phil.panda.dao.UserDao;
import com.phil.panda.pojo.User;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userDao;
	
	@Override
	public List<User> getAllUser() throws Exception {
		return userDao.findAll();
	}

	@Override
	public void save(User u) throws Exception {
		userDao.save(u);
	}

	@Override
	public void delete(User u) throws Exception {
		userDao.delete(u);
	}
	
	@Override
	public void delete(Integer userId) throws Exception {
		userDao.deleteById(userId);
	}

	@Override
	public List<User> findByEmail(String email) throws Exception {
		return userDao.findByEmail(email);
	}

	@Override
	public User findOne(Integer userId) throws Exception {
//		return userDao.findById(userId).get();
		return userDao.getOne(userId);
	}

}
