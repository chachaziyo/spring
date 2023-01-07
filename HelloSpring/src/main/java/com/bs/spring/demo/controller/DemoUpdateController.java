package com.bs.spring.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bs.spring.demo.model.service.DemoUpdateService;
import com.bs.spring.member.vo.Demo;

@Controller
public class DemoUpdateController {

	private DemoUpdateService updateService;
	
	@Autowired
	public DemoUpdateController(DemoUpdateService updateService) {
		this.updateService=updateService;
	}
	
	@RequestMapping("/demo/updatedemo.do")
	public String updateViewpage(@RequestParam int no, Model m) {
		Demo d = updateService.selectDemo(no);
		m.addAttribute("demo", d);
		return "demo/demoupdate.jsp";
	}
	
	@RequestMapping("/demo/updatedemoend.do")
	public String updateDemo(Demo demo) {
		int result = updateService.updateDemo(demo);
		return "redirect:/demo/demolist.do";
	}
	
	
	
	
	
	
	
	
	
	
}
