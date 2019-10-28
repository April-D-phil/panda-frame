package com.phil.panda.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.phil.panda.pojo.User;

public interface UserDao extends JpaRepository<User, Integer>{

	@Query("from user where email like concat('%', :email, '%') ")
	List<User> findByEmail(@Param("email") String email) throws Exception;
	
}
