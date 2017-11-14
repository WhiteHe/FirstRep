package com.pingfeihe
.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pingfeihe.dao.ResourceDao;
import com.pingfeihe.dao.UserDao;
import com.pingfeihe.po.Menu;
import com.pingfeihe.po.Permission;
import com.pingfeihe.po.Resource;
import com.pingfeihe.po.Role;
import com.pingfeihe.po.User;

@Controller
public class IndexContrller {

	@Autowired
	private ResourceDao resourceDao;
	
	@Autowired
	private UserDao userDao;
	
	@RequestMapping("/index.do")
	public String index(HttpSession session){
		User user = (User) session.getAttribute("principal");
		user = userDao.findOne(user.getId());
		Set<Role> roles = user.getRoles();
		List<Integer> roleIds = new ArrayList<Integer>();
		for (Role role : roles) {
			roleIds.add(role.getId());
		}
		//
		if (session.getAttribute("sysmenus")==null) {
			List<Resource> resources = resourceDao.findByRoleId(roleIds.toArray(new Integer[roleIds.size()]));
			Set<Menu> menus = new TreeSet<Menu>();
			Set<String> permissions = new HashSet<String>();
			if(resources!=null && resources.size()>0){
				for (Resource resource : resources) {
					menus.add(resource.getMenu());
					Permission permission = resource.getPermission();
					if(permission!=null){
						permissions.add(permission.getConstant());
					}
				}
			}
			session.setAttribute("sysmenus", menus);
			session.setAttribute("permissions", permissions);
        }
		return "index";
	}
}
