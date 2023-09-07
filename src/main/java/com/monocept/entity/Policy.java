package com.monocept.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Policy {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "policy_id")
	private int policyId;

	private String policyName;

	@Lob
	private byte[] imageData;

	@Column(name = "new_comm")
	private double newCommission;

	@Column(name = "installment_comm")
	private double installmentCommission;

	private String description;

	private Status status;

	@ManyToOne
	@JoinColumn(name = "fk_insurance_id")
	@JsonIgnoreProperties(value = "policies")
	private InsurancePlan plan;

	@OneToOne(cascade = CascadeType.ALL)
	private PolicyDetails details;

	@OneToMany
	@JoinColumn(name = "fk_policy_id", referencedColumnName = "policy_id")
	private List<InsuranceAccount> accounts;
	
	

	public Policy(int policyId, String policyName, byte[] imageData, double newCommission, double installmentCommission,
			String description, Status status, InsurancePlan plan, PolicyDetails details,
			List<InsuranceAccount> accounts) {
		super();
		this.policyId = policyId;
		this.policyName = policyName;
		this.imageData = imageData;
		this.newCommission = newCommission;
		this.installmentCommission = installmentCommission;
		this.description = description;
		this.status = status;
		this.plan = plan;
		this.details = details;
		this.accounts = accounts;
	}

	
	public Status getStatus() {
		return status;
	}


	public void setStatus(Status status) {
		this.status = status;
	}


	public List<InsuranceAccount> getAccounts() {
		return accounts;
	}


	public void setAccounts(List<InsuranceAccount> accounts) {
		this.accounts = accounts;
	}


	public InsurancePlan getPlan() {
		return plan;
	}

	public void setPlan(InsurancePlan plan) {
		this.plan = plan;
	}

	public PolicyDetails getDetails() {
		return details;
	}

	public void setDetails(PolicyDetails details) {
		this.details = details;
	}

	public Policy() {
		super();
	}

	public int getPolicyId() {
		return policyId;
	}

	

	public void setPolicyId(int policyId) {
		this.policyId = policyId;
	}

	public byte[] getImageData() {
		return imageData;
	}

	public void setImageData(byte[] imageData) {
		this.imageData = imageData;
	}

	public double getNewCommission() {
		return newCommission;
	}

	public void setNewCommission(double newCommission) {
		this.newCommission = newCommission;
	}

	public double getInstallmentCommission() {
		return installmentCommission;
	}

	public void setInstallmentCommission(double installmentCommission) {
		this.installmentCommission = installmentCommission;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}



	public String getPolicyName() {
		return policyName;
	}

	public void setPolicyName(String policyName) {
		this.policyName = policyName;
	}

}
