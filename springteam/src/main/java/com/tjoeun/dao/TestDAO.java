package com.tjoeun.dao;

import org.springframework.stereotype.Repository;

@Repository
public class TestDAO {
	public String testMethod1() {
		return "TestDAO 의 testMethod1() 메소드에서 반환하는 문자열";
	}
}
