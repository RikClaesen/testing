package com.example.repository;

import org.springframework.stereotype.Repository;

import com.example.domain.Greeting;

@Repository
public class TestRepositoryImpl implements TestRepository {

	@Override
	public String getAgentId() {
		// TODO Auto-generated method stub
		return "007";
	}
	
	@Override
	public String updateAgent(Greeting greeting){
		// TODO
		return "OK";
	}

}
