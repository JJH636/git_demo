package cn.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.pojo.dev_user;
import cn.service.user.userService;

@Controller
@RequestMapping("/dev")
public class userController {
	private Logger logger=Logger.getLogger(userController.class);
	
	@Autowired
	private userService userService;
	
	@RequestMapping(value="/index")
	public String login() {
		return "index";
	}
	
	@RequestMapping(value="/login")
	public String login1() {
		return "devlogin";
	}
	
	@RequestMapping(value="/dologin",method=RequestMethod.POST)
	public String doLogin(@RequestParam String devCode,@RequestParam String devPassword,HttpSession session,HttpServletRequest request) {
		dev_user user= userService.login(devCode, devPassword);
		if(null!=user) {
			session.setAttribute("devUser", user);
			return "/developer/main";
		}else{
			request.setAttribute("error", "用户名或密码不正确");
			return "devlogin";
		}
	}
	@RequestMapping(value="/logout")
	public String out(HttpSession session){
		session.removeAttribute("devUser");
		return "devlogin";
	}
}
