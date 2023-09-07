package com.monocept.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.monocept.entity.Agent;
import com.monocept.entity.Employee;
import com.monocept.entity.Request;
import com.monocept.exception.CustomerNotFoundException;
import com.monocept.service.EmailService;
import com.monocept.service.IAgentService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1/agent")
public class AgentController {

	@Autowired
	private IAgentService agentService;

	@Autowired
	private EmailService emailService;

	@GetMapping
	public List<Agent> findAll() {
		List<Agent> agents = agentService.findAll();
		return agents;
	}

	@PostMapping("/save")
	public Agent saveAgent(@RequestBody Agent agent) {
		return agentService.save(agent);
	}

	@PostMapping("/saveall")
	public List<Agent> saveAllAgents(@RequestBody List<Agent> agentList) {
		return agentService.saveAll(agentList);
	}

	@GetMapping("/getbyid/{agentId}")
	public Agent findById(@PathVariable int agentId) {
		Agent agent = null;
		agent = agentService.findById(agentId);
		if (agent == null) {
			throw new CustomerNotFoundException("Agent with id " + agentId + " is not found");
		}
		return agent;
	}

	@PutMapping("/delete/{id}")
	public Agent delete(@PathVariable int id) {
		return agentService.delete(id);
	}

	@PutMapping("/update/{id}")
	public Agent update(@PathVariable int id, @RequestBody Agent agent) {
		return agentService.update(id, agent);
	}

	@PostMapping("/sendemail")
	public String sendEmail(@RequestBody Request request) {
		return emailService.sendEmail(request.getTo(), request.getSubject(), request.getText());
	}
}