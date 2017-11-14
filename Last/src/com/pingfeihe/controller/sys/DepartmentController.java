package com.pingfeihe.controller.sys;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pingfeihe.commons.Pager;
import com.pingfeihe.po.Dept;
import com.pingfeihe.service.DepartmentService;

@Controller
@RequestMapping("/department")
public class DepartmentController {

	@Autowired
	private DepartmentService departmentService;

	@RequestMapping("/findAll.do")
	public String findAll(Integer current ,HttpServletRequest request) {
		if(current == null || current <1){
			current = 1;
		}
		Pager pager = this.departmentService.find4Page(current, 10);
		request.setAttribute("pager", pager);
		return "sys/departments/index";
	}

	@RequestMapping("/save.do")
	public String save(Dept department) {
		if (department != null) {
			this.departmentService.save(department);
		}
		return "redirect:/department/findAll.do";
	}

	@RequestMapping("/delete.do")
	public String delete(Dept department) {
		Integer id = null;
		if (department != null) {
			id = department.getId();
		}
		if (id != null) {
			this.departmentService.delete(id);
		}
		return "redirect:/department/findAll.do";
	}
	@RequestMapping("/updateInView.do")
	public String  updateInView(Integer id,HttpServletRequest request){
		
		if (id != null) {
			Dept department = this.departmentService.findOne(id);
			 request.setAttribute("department", department);
			 return "sys/departments/update";
		}
		return "commons/error";
	}
	
	@RequestMapping("/update.do")
	public String update(Dept department){
		if(department!=null){
			this.departmentService.update(department);
		}
		return "redirect:/department/findAll.do";
	}
}
