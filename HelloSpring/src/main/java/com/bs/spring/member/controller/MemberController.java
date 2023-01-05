package com.bs.spring.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bs.spring.member.service.MemberService;

@Controller
public class MemberController {

	//controller는 service에 의존한다! 
	@Autowired //service메소드를 이용하기 위하여!
	private MemberService service;
	
	@RequestMapping("/test/")
	public void test() {
		System.out.println("controller - test() 실행");
		service.test();
		
	}
	
	
}
