package com.phil.panda.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class DefaultController {
	
	@RequestMapping("/")
	public String index(HttpServletRequest req, HttpServletResponse res) {
		return "index";//这里一定不能用/index，因为maven打包后，运行jar会出错
	}

}
