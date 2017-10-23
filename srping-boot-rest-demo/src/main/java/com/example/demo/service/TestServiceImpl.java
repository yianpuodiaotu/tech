package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.TestEntity;
import com.example.demo.mapper.TestMapper;

@Service
public class TestServiceImpl implements TestService {
	
	
	@Autowired
	private TestMapper testMapper;
	
	@Override
	public int addTest(TestEntity testEntity) {
		return testMapper.addTest(testEntity);
	}

}
