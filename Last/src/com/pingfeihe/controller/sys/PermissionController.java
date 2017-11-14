package com.pingfeihe.controller.sys;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pingfeihe.commons.Pager;
import com.pingfeihe.po.Permission;
import com.pingfeihe.service.PermissionService;

@Controller
@RequestMapping("/permission")
public class PermissionController {

	@Autowired
	private PermissionService permissionService;

	@RequestMapping("/findAll.do")
	public String findAll(Integer current ,HttpServletRequest request) {
		if(current == null || current <1){
			current = 1;
		}
		Pager pager = this.permissionService.find4Page(current, 10);
		
		request.setAttribute("pager", pager);
		return "sys/permissions/index";
	}

	@RequestMapping("/save.do")
	public String save(Permission permission) {
		if (permission != null) {
			this.permissionService.save(permission);
		}
		return "redirect:/permission/findAll.do";
	}

	@RequestMapping("/delete.do")
	public String delete(Permission permission) {
		Integer id = null;
		if (permission != null) {
			id = permission.getId();
		}
		if (id != null) {
			this.permissionService.delete(id);
		}
		return "redirect:/permission/findAll.do";
	}
	@RequestMapping("/updateInView.do")
	public String  updateInView(Integer id,HttpServletRequest request){
		
		if (id != null) {
			Permission permission = this.permissionService.findOne(id);
			 request.setAttribute("permission", permission);
			 return "sys/permissions/update";
		}
		return "commons/error";
	}
	
	@RequestMapping("/update.do")
	public String update(Permission permission){
		if(permission!=null){
			this.permissionService.update(permission);
		}
		return "redirect:/permission/findAll.do";
	}
}
