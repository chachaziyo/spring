package com.bs.spring.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bs.spring.member.dao.MemberDao;

@Service //중간메개체로써 서비스 기능을 한다!
public class MemberServiceImpl implements MemberService{

	@Autowired
	private MemberDao dao;
	
	@Override
	public void test() {
		System.out.println("service - test() 실행");
		dao.test();
	}
}
