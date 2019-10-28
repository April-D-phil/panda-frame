package com.phil.panda.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;


@Data
@Entity(name="user")//这里必须写，因为JPA在使用注解时，需要在这里找到
@Table(name="user")
public class User implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3177161353185770369L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="userid",nullable=false)
	private Integer userId;
	
	@Column(name="username",nullable=false)
	private String username;
	
	@Column(name="email",nullable=false)
	private String email;
	
	@Column(name="password",nullable=false)
	private String password;
	
	@Column(name="sex")
	private Integer sex;
	
	@Column(name="updatetime",nullable=false)
	private Date updateTime;
	
	@Column(name="phone")
	private String phone;
	
	@Column(name="age")
	private Integer age;

}
