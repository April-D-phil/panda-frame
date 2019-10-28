package com.phil.panda.service;

import java.util.List;

import com.phil.panda.pojo.User;

public interface UserService {
	
	List<User> getAllUser() throws Exception;
	
	void save(User u) throws Exception;
	
	void delete(User u) throws Exception;
	
	void delete(Integer userId) throws Exception;
	
	List<User> findByEmail(String email) throws Exception;
	
	User findOne(Integer userId) throws Exception;

}
