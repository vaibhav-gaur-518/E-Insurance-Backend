package com.monocept.service;

import java.util.List;

import com.monocept.entity.Agent;
import com.monocept.entity.Employee;

public interface IAgentService {

	List<Agent> findAll();

	Agent save(Agent agent);

	Agent findById(int agentId);

	void deleteById(int agentId);

	List<Agent> saveAll(List<Agent> agentList);

	Agent delete(int id);

	Agent update(int id, Agent agent);

}
