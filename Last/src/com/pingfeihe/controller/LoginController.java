package com.pingfeihe.controller;

import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pingfeihe.po.User;
import com.pingfeihe.service.UserService;
import com.pingfeihe.util.SendEmail;

@Controller
@RequestMapping("/login")
public class LoginController {

	// 查询权限字符串
	private static final String PER_SQL = "SELECT DISTINCT " + "t5.constant FROM crm_user t1 " + "LEFT OUTER JOIN crm_user_role t2 ON t1.id = t2.user_id "
	        + "LEFT OUTER JOIN crm_role t3 ON t3.id = t2.role_id " + "LEFT OUTER JOIN crm_role_resource t4 ON t4.role_id = t3.id "
	        + "RIGHT OUTER JOIN crm_permission t5 ON t5.id = t4.permission_id " + "LEFT OUTER JOIN crm_menu t6 ON t6.id = t5.menu_id "
	        + "WHERE t1.email =? AND t3.enabled = 1 AND t5.enabled = 1 AND t6.enabled = 1";

	@Autowired
	private UserService userService;

	@Autowired
	private SendEmail sendEmail;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@RequestMapping("/login.do")
	public String login(String email, String password, HttpServletRequest request) {
		String msg = "";
		request.setAttribute("email", email);
		request.setAttribute("password", password);
		if (!(email != null && StringUtils.hasText(email))) {
			msg = "请输入你的邮箱账号";
			request.setAttribute("msg", msg);
			return "forward:/login.jsp";
		}
		if (!(password != null && StringUtils.hasText(password))) {
			msg = "你还没有输入密码,请重试";
			request.setAttribute("msg", msg);
			return "forward:/login.jsp";
		}
		User user = userService.findByName(email);
		if (user != null && Objects.equals(password, user.getPassword())) {
			request.getSession().setAttribute("principal", user);
			request.getSession().setAttribute("permission", getAuthorization(email));
			return "redirect:/index.do";
		} else {
			msg = "用户名或密码错误,请重试!";
			request.setAttribute("msg", msg);
			return "forward:/login.jsp";
		}
	}

	@RequestMapping("/logout.do")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/login.jsp";
	}

	@RequestMapping("/findPassword.do")
	public String findPassword() {
		return "sys/users/verifyAccount";
	}

	@RequestMapping(value = "/verifyAccount.do", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String verifyAccount(String email, HttpServletRequest request) {
		String msg = "";
		User user = null;
		if (email != null) {
			user = this.userService.findByName(email);
		}

		if (user != null) {
			sendEmail.send(email, user.getPassword());
			msg = "{\"msg\":\"已经将密码发送至你的邮箱\"}";
			return msg;
		} else {
			msg = "{\"msg\":\"没有账户绑定该邮箱，请检查你的邮箱\"}";
			return msg;
		}

	}

	// 获取当前用户的权限并放入list
	private List<String> getAuthorization(String email) {
		return this.jdbcTemplate.queryForList(PER_SQL, new Object[] { email }, String.class);
	}

}
