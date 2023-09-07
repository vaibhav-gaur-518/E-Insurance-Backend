package com.monocept.entity;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Agent {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "agent_id")
	private int agentId;

	@Column(name = "agent_code")
	private String agentCode;

	private String qualification;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	User user;

	@JsonIgnoreProperties(value = "agent")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_agent_id", referencedColumnName = "agent_id")
	private Set<Customer> customers = new HashSet<Customer>();

	@OneToMany
	@JoinColumn(name = "fk_agent_id", referencedColumnName = "agent_id")
	private List<CommissionRecord> records;

	public Agent() {

	}

	public Agent(String agentCode, String qualification) {
		super();
		this.agentCode = agentCode;
		this.qualification = qualification;
	}

	public int getAgentId() {
		return agentId;
	}

	public void setAgentId(int agentId) {
		this.agentId = agentId;
	}

	public String getAgentCode() {
		return agentCode;
	}

	public void setAgentCode(String agentCode) {
		this.agentCode = agentCode;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(Set<Customer> customers) {
		this.customers = customers;
	}

}
