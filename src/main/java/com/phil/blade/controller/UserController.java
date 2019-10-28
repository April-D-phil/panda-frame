package com.phil.blade.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.phil.blade.pojo.User;
import com.phil.blade.service.UserService;

@Controller("bladeUserController")
@RequestMapping("/blade/user")
public class UserController {

	@Autowired
	@Qualifier("bladeUserService")
	private UserService userService; 
	
	@RequestMapping("findByEmail")
	@ResponseBody
	public Map<String,Object> findByEmail(String email) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("rtn", "fail");
		try {
			User u = userService.findByEmail(email);
			map.put("data", u);
			map.put("rtn", "ok");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
}
