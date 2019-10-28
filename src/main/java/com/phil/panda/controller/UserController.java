package com.phil.panda.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.phil.panda.pojo.User;
import com.phil.panda.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@ResponseBody
	@RequestMapping("/findAll")
	public Map<String,Object> findAll(HttpServletRequest req, HttpServletResponse res){
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("rtn", "fail");
		try {
			List<User> list = userService.getAllUser();
			map.put("data", list);
			map.put("rtn", "ok");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("info", "查询失败！");
		}
		return map;
	}
	
}
