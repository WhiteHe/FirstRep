package com.pingfeihe.controller.sys;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pingfeihe.commons.Pager;
import com.pingfeihe.po.Menu;
import com.pingfeihe.po.Permission;
import com.pingfeihe.po.Resource;
import com.pingfeihe.po.Role;
import com.pingfeihe.service.MenuService;
import com.pingfeihe.service.RoleService;
import com.pingfeihe.vo.ResourceVo;
import com.pingfeihe.vo.RoleResource;

@Controller
@RequestMapping("/role")
public class RoleController {

	@Autowired
	private RoleService roleService;
	@Autowired
	private MenuService menuService;

	@RequestMapping("/findAll.do")
	public String findAll(Integer current ,HttpServletRequest request) {
		if(current == null || current <1){
			current = 1;
		}
		Pager pager = this.roleService.find4Page(current, 10);
		
		request.setAttribute("pager", pager);
		return "sys/roles/index";
	}

	@RequestMapping("/save.do")
	public String save(Role role) {
		if (role != null) {
			this.roleService.save(role);
		}
		return "redirect:/role/findAll.do";
	}

	@RequestMapping("/delete.do")
	public String delete(Role role) {
		Integer id = null;
		if (role != null) {
			id = role.getId();
		}
		if (id != null) {
			this.roleService.delete(id);
		}
		return "redirect:/role/findAll.do";
	}

	@RequestMapping("/updateInView.do")
	public String updateInView(Integer id, HttpServletRequest request) {

		if (id != null) {
			Role role = this.roleService.findOne(id);
			request.setAttribute("role", role);
			return "sys/roles/update";
		}
		return "commons/error";
	}

	@RequestMapping("/update.do")
	public String update(Role role) {
		if (role != null) {
			this.roleService.update(role);
		}
		return "redirect:/role/findAll.do";
	}

	@RequestMapping("/allocateInView.do")
	@ResponseBody
	public List<ResourceVo> allocateInView(Integer id){
		Set<Resource> resources = null;
		Set<Menu> menus = new HashSet<Menu>();
		Set<Permission> permissions = new HashSet<Permission>();
		if(id!=null){
			Role role = this.roleService.findOne(id);
			if(role!=null){
				resources = role.getResources();
			}
		}
		for (Resource resource : resources) {
			menus.add(resource.getMenu());
			permissions.add(resource.getPermission());
		}
		List<Menu> ms = menuService.findAll();
		List<ResourceVo> resourceVos = new ArrayList<ResourceVo>();
		for (Menu menu : ms) {
			ResourceVo resourceVo = new ResourceVo(menu.getId(), menu.getParent()==null?0:menu.getParent().getId(),menu.getName() );
			resourceVo.setChkDisabled(!menu.getEnabled());
			if(menus.contains(menu)){
				resourceVo.setChecked(true);
			}
			resourceVos.add(resourceVo);
			Set<Permission> ps = menu.getPermissions();
			for (Permission permission : ps) {
				 ResourceVo resourceVo2 = new ResourceVo(permission.getId()*1000,permission.getMenu().getId(),permission.getName());
				 resourceVo2.setChkDisabled(!permission.getEnabled());
				 if(permissions.contains(permission)){
					 resourceVo2.setChecked(true);
				 }
				 resourceVo2.setType("permission");
				 resourceVos.add(resourceVo2);
			}
		}
		return resourceVos; 
	}
	@RequestMapping("/allocate.do")
	public String allocate(Integer id,RoleResource resource){
		if(id != null){
			this.roleService.allocate(id,resource);
		}
		return "redirect:/role/findAll.do";
	}
}
