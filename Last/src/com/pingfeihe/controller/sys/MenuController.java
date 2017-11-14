package com.pingfeihe.controller.sys;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pingfeihe.commons.Pager;
import com.pingfeihe.po.Menu;
import com.pingfeihe.service.MenuService;

@Controller
@RequestMapping("/menu")
public class MenuController {

	@Autowired
	private MenuService menuService;

	@RequestMapping("/findAll.do")
	public String findAll(Integer current ,HttpServletRequest request) {
		if(current == null || current <1){
			current = 1;
		}
		Pager pager = this.menuService.find4Page(current, 10);
		
		request.setAttribute("pager", pager);
		return "sys/menus/index";
	}
	

	@RequestMapping("/save.do")
	public String save(Menu menu) {
		if (menu != null) {
			this.menuService.save(menu);
		}
		return "redirect:/menu/findAll.do";
	}

	@RequestMapping("/delete.do")
	public String delete(Menu menu) {
		Integer id = null;
		if (menu != null) {
			id = menu.getId();
		}
		if (id != null) {
			this.menuService.delete(id);
		}
		return "redirect:/menu/findAll.do";
	}
	@RequestMapping("/updateInView.do")
	public String  updateInView(Integer id,HttpServletRequest request){
		
		if (id != null) {
			Menu menu = this.menuService.findOne(id);
			List<Menu> menus = this.menuService.findAll();
			request.setAttribute("menus", menus);
			request.setAttribute("menu", menu);
			return "sys/menus/update";
		}
		return "commons/error";
	}
	
	@RequestMapping("/update.do")
	public String update(Menu menu){
		if(menu!=null){
			this.menuService.update(menu);
		}
		return "redirect:/menu/findAll.do";
	}
}
