package com.cafe24.jblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cafe24.jblog.service.UserService;
import com.cafe24.jblog.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/join")
	public String join() {
		
		return "user/join";
	}
	
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String join(@ModelAttribute UserVo userVo) {
		
		Boolean insertCheck = userService.insertUser(userVo);
		
		if(insertCheck) return "user/joinsuccess";
		
		return "error/500";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login() {
		return "user/login";
	}
	@RequestMapping(value="/auth", method=RequestMethod.POST)
	public void logins() {}
	
	@RequestMapping("/checkId")
	@ResponseBody
	public String checkId(@RequestParam(value="id", required=true, defaultValue="") String id) {
		Boolean exist = userService.existId(id);
		
		String result = "";
		if(exist) {
			result = "1";
		}else {
			result = "2";
		}
		return result;
	}
}
