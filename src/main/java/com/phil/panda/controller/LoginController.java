package com.phil.panda.controller;

import java.util.Date;
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
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	private UserService userService;
	
	/**
	 * 注册页面
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping("/register/page")
	public String register(HttpServletRequest req, HttpServletResponse res) {
		return "/panda/login/registerPage";
	}
	
	/**
	 * 用户注册
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping("/checkRegister")
	@ResponseBody
	public Map<String,Object> checkRegister(HttpServletRequest req, HttpServletResponse res) {
		Map<String,Object> map = new HashMap<String, Object>();
		String rtn = "fail";
		String msg = "";
		try {
			String username = req.getParameter("username");
			String email = req.getParameter("email");
			String password = req.getParameter("password");
			List<User> list = userService.findByEmail(email);
			if(list.isEmpty() || list.size()==0) {
				User u = new User();
				u.setUsername(username);
				u.setEmail(email);
				u.setPassword(password);
				u.setUpdateTime(new Date());
				userService.save(u);
				msg = "注册成功！";
				rtn = "ok";
			}else {
				msg = "邮箱已注册！";
			}
			map.put("msg", msg);
			map.put("rtn", rtn);
		}catch(Exception e) {
			e.printStackTrace();
			map.put("msg", "服务器繁忙！");
		}
		return map;
	}
	
	/**
	 * 登录验证
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping("/checkLogin")
	@ResponseBody
	public Map<String,Object> checkLogin(HttpServletRequest req, HttpServletResponse res) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("rtn", "fail");
		try {
			String email = req.getParameter("email");
			String password = req.getParameter("password");
			map.put("rtn", "ok");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return map;
	}

}
