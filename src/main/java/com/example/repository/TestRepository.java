package com.example.repository;

import com.example.domain.Greeting;

public interface TestRepository {
	public String getAgentId();
	public String updateAgent(Greeting greeting);

}
