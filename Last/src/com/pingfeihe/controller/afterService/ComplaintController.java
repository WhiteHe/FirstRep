package com.pingfeihe.controller.afterService;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pingfeihe.commons.Pager;
import com.pingfeihe.po.Complaint;
import com.pingfeihe.po.User;
import com.pingfeihe.service.ComplaintService;

@Controller
@RequestMapping("/complaint")
public class ComplaintController {

	@Autowired
	private ComplaintService complaintService;

	@RequestMapping("/findAll.do")
	public String findAll(HttpServletRequest request, Integer current) {
		if (current == null) {
			current = 1;
		}
		Pager pager = this.complaintService.find4Page(current, 10);
		request.setAttribute("pager", pager);
		return "serv/complaint/index";
	}

	@RequestMapping("/save.do")
	public String save(Complaint complaint , HttpServletRequest request){
		if(complaint == null){
			return "commons/error";
		}
		User principal = (User) request.getSession().getAttribute("principal");
		complaint.setRecorder(principal.getEmail());
		this.complaintService.save(complaint);
		return "redirect:/complaint/findAll.do";
	}
}
