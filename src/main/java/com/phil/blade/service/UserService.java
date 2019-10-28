package com.phil.blade.service;

import com.phil.blade.pojo.User;

public interface UserService {
	
	User findByEmail(String email) throws Exception;

}
