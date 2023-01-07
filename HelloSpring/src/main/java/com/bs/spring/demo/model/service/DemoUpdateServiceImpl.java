package com.bs.spring.demo.model.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bs.spring.demo.model.dao.DemoUpdateDao;
import com.bs.spring.member.vo.Demo;

@Service
public class DemoUpdateServiceImpl implements DemoUpdateService {
	
	private DemoUpdateDao updateDao;
	private SqlSessionTemplate session;
	
	

	@Autowired
	public void setUpdateDao(DemoUpdateDao updateDao) {
		this.updateDao = updateDao;
	}

	@Autowired
	public void setSession(SqlSessionTemplate session) {
		this.session = session;
	}

	@Override
	public Demo selectDemo(int no) {
		// TODO Auto-generated method stub
		return updateDao.selectDemo(session,no);
	}

	@Override
	public int updateDemo(Demo d) {
		// TODO Auto-generated method stub
		return updateDao.updateDemo(session,d);
	}


	
}
