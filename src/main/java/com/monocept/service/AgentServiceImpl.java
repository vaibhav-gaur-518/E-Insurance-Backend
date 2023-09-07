package com.monocept.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.monocept.entity.Agent;
import com.monocept.entity.Customer;
import com.monocept.entity.Employee;
import com.monocept.entity.Status;
import com.monocept.exception.UserNotFoundException;
import com.monocept.repository.AgentRepository;
import com.monocept.repository.CustomerRepository;

@Service
public class AgentServiceImpl implements IAgentService {

	@Autowired
	AgentRepository agentRepo;

	@Autowired
	CustomerRepository customerRepo;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public List<Agent> findAll() {
		return agentRepo.findAll();
	}

	@Override
	public Agent save(Agent agent) {
		agent.getUser().setPassword(bCryptPasswordEncoder.encode(agent.getUser().getPassword()));
		return agentRepo.save(agent);
	}

	@Override
	public Agent findById(int agentId) {
		return agentRepo.findById(agentId).get();
	}

	@Override
	public void deleteById(int agentId) {
		agentRepo.deleteById(agentId);

	}

	@Override
	public List<Agent> saveAll(List<Agent> agentList) {
		return agentRepo.saveAll(agentList);
	}

	@Override
	public Agent delete(int id) {
		Optional<Agent> agentById = agentRepo.findById(id);
		if(!agentById.isPresent())
		{
			throw new UserNotFoundException("Employee with id  not found");
		}
		
		agentById.get().getUser().setStatus(Status.INACTIVE);
		return agentRepo.save(agentById.get());
	}

	@Override
	public Agent update(int id, Agent agent) {
		Optional<Agent> agentById = agentRepo.findById(id);
		if(!agentById.isPresent())
		{
			throw new UserNotFoundException("Employee with id"+agent.getAgentId()+ " not found");
		}	

		agentById.get().getUser().setName(agent.getUser().getName());
		agentById.get().getUser().setEmail(agent.getUser().getEmail());
		agentById.get().getUser().setAddress(agent.getUser().getAddress());
		agentById.get().getUser().setStatus(agent.getUser().getStatus());

		
		return agentRepo.save(agentById.get());
	}
}
