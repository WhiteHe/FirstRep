package com.pingfeihe.controller.sys;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pingfeihe.commons.Pager;
import com.pingfeihe.po.User;
import com.pingfeihe.service.RoleService;
import com.pingfeihe.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;

	@RequestMapping("/findAll.do")
	public String findAll(HttpServletRequest request, Integer current) {
		if (current == null || current < 0) {
			current = 0;
		}
		Pager pager = this.userService.find4Page(current, 10);
		request.setAttribute("pager", pager);
		return "sys/users/index";
	}

	@RequestMapping("/save.do")
	public String save(User user) {
		if (user != null) {
			this.userService.save(user);
		}
		return "redirect:/user/findAll.do";
	}

	@RequestMapping("/delete.do")
	public String delete(User user) {
		Integer id = null;
		if (user != null) {
			id = user.getId();
		}
		if (id != null) {
			this.userService.delete(id);
		}
		return "redirect:/user/findAll.do";
	}

	@RequestMapping("/updateInView.do")
	public String updateInView(Integer id, HttpServletRequest request) {

		if (id != null) {
			User user = this.userService.findOne(id);
			request.setAttribute("user", user);
			return "sys/users/update";
		}
		return "commons/error";
	}

	@RequestMapping("/update.do")
	public String update(User user) {
		if (user != null) {
			this.userService.update(user);
		}
		return "redirect:/user/findAll.do";
	}

	@RequestMapping("/allocateInView.do")
	public String allocateInView(Integer id, HttpServletRequest request) {
		if (id == null) {
			return "commons/error";
		}
		User user = this.userService.findOne(id);
		if (user == null) {
			return "redirect:/user/findAll.do";
		}
		request.setAttribute("user", user);
		request.setAttribute("roles", this.roleService.findAll());
		return "sys/users/allocate";
	}
	@RequestMapping("/allocate.do")
	public String allocate(Integer[] roleIds, Integer id, HttpServletRequest request) {
		if (id == null) {
			return "commons/error";
		}
		this.userService.allocate(id,roleIds);
		return "redirect:/user/findAll.do";
	}
	@RequestMapping("/changePasswordInView.do")
	public String changePasswordInView(Integer id, HttpServletRequest request){
		if(id==null){
			return "commons/error";
		}
		User user = this.userService.findOne(id);
		request.setAttribute("user", user);
		return "sys/users/changePassword";
	}
	@RequestMapping("/change.do")
	public String change(Integer id,String password,String changePassword,HttpServletRequest request){
		if(id == null){
			return "commons/error";
		}
		if(password==null || password.trim().length()<1 || changePassword == null || changePassword.trim().length()<1){
			request.setAttribute("msg", "信息没有填写完整");
			return "sys/users/changePassword";
		}
		User user = this.userService.findOne(id);
		if(!password.equals(user.getPassword())){
			request.setAttribute("msg", "原始密码输入错误");
			return "sys/users/changePassword";
		}
		user.setPassword(changePassword);
		this.userService.update(user);
		request.setAttribute("email", user.getEmail());
		return "forward:/login.jsp";
	}
}
