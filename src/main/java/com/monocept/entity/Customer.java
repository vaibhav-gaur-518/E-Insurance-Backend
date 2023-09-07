package com.monocept.entity;

import java.sql.Date;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "customer_id")
	private int customerId;

	private int agentId;
	
	@Column(name = "date_of_birth")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private Date dateOfBirth;

	private String phone;
	private String state;
	private String city;
	private int pincode;
	private String nominee;
	private String nomineeRelation;
	

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	User user;

	@ManyToOne
	@JoinColumn(name = "fk_agent_id")
	private Agent agent;

	@JsonIgnoreProperties(value = "customer")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_customer_id", referencedColumnName = "customer_id")
	private Set<InsuranceAccount> insuranceAccounts = new HashSet<InsuranceAccount>();

	@JsonIgnoreProperties(value = "customer")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_customer_id", referencedColumnName = "customer_id")
	private Set<Document> documents = new HashSet<Document>();

	public Customer() {

	}

	public Customer(int customerId, Date dateOfBirth, String phone, String state, String city, int pincode,
			String nominee, String nomineeRelation, int agentId, User user, Agent agent,
			Set<InsuranceAccount> insuranceAccounts, Set<Document> documents) {
		super();
		this.customerId = customerId;
		this.dateOfBirth = dateOfBirth;
		this.phone = phone;
		this.state = state;
		this.city = city;
		this.pincode = pincode;
		this.nominee = nominee;
		this.nomineeRelation = nomineeRelation;
		this.agentId = agentId;
		this.user = user;
		this.agent = agent;
		this.insuranceAccounts = insuranceAccounts;
		this.documents = documents;
	}

	public int getAgentId() {
		return agentId;
	}

	public void setAgentId(int agentId) {
		this.agentId = agentId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getPincode() {
		return pincode;
	}

	public void setPincode(int pincode) {
		this.pincode = pincode;
	}

	public String getNominee() {
		return nominee;
	}

	public void setNominee(String nominee) {
		this.nominee = nominee;
	}

	public String getNomineeRelation() {
		return nomineeRelation;
	}

	public void setNomineeRelation(String nomineeRelation) {
		this.nomineeRelation = nomineeRelation;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Agent getAgent() {
		return agent;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
	}

	public Set<InsuranceAccount> getInsuranceAccounts() {
		return insuranceAccounts;
	}

	public void setInsuranceAccounts(Set<InsuranceAccount> insuranceAccounts) {
		this.insuranceAccounts = insuranceAccounts;
	}

	public Set<Document> getDocuments() {
		return documents;
	}

	public void setDocuments(Set<Document> documents) {
		this.documents = documents;
	}

}
