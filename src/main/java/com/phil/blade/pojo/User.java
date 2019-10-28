package com.phil.blade.pojo;

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
@Entity(name="user")
@Table(name="user")
public class User implements Serializable{
	
	private static final long serialVersionUID = 3098421424891082223L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="user_id", nullable=false)
	private Integer userId;
	
	@Column(name="age")
	private Integer age;
	
	@Column(name="email", nullable=false)
	private String email;
	
	@Column(name="password", nullable=false)
	private String password;
	
	@Column(name="phone")
	private String phone;
	
	@Column(name="sex")
	private Integer sex;
	
	@Column(name="update_time", nullable=false)
	private Date updateTime;
	
	@Column(name="username", nullable=false)
	private String username;

}
