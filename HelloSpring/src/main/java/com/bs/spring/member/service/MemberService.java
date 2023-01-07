package com.bs.spring.member.service;

import org.springframework.web.servlet.ModelAndView;

import com.bs.spring.member.vo.Member;

public interface MemberService {
	void test();//추상메소드!

	Member selectMemberById(Member m);

	int insertMember(Member m);

	
}
