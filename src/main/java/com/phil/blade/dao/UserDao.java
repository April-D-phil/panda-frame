package com.phil.blade.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.phil.blade.pojo.User;

@Repository("bladeUserDao")
public interface UserDao extends JpaRepository<User,Integer>{

	User findByEmail(String email) throws Exception;
	
}
