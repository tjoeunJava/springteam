package com.tjoeun.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tjoeun.dao.TestDAO;

@Service
public class TestService {
	
	@Autowired
	TestDAO testDAO;
	
	public String testMethod1() {
		// return "TestService 의 testMethod1() 메소드에서 반환하는 문자열";
		String str1 = testDAO.testMethod1();
		return str1;
	}

}
