package com.monocept.entity;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class InsuranceAccount {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "account_number")
	private int accountNumber;

	@Column(name = "insurance_type")
	private String insuranceType;

	@Column(name = "insurance_scheme")
	private String insuranceScheme;

	@Column(name = "date_created")
	private LocalDate dateCreated = LocalDate.now();

	@Column(name = "maturity_date")
	private LocalDate maturityDate;
	
	@Column(name = "duration")
	private int duration;

	@Column(name = "premium_type")
	private String premiumType;

	@Column(name = "total_premium_amount")
	private double totalPremiumAmount;
	
	@Column(name = "total_premium_amount_left")
	private double totalPremiumAmountLeft;
	
	@Column(name = "installment_amount")
	private double installmentAmount;

	@Column(name = "profit_ratio")
	private double profitRatio;

	@Column(name = "sum_assured")
	private double sumAssured;

	@ManyToOne
	@JoinColumn(name = "fk_customer_id")
	@JsonIgnoreProperties(value = "insuranceAccounts")
	private Customer customer;

	@ManyToOne
	@JsonIgnoreProperties(value = "accounts")
	@JoinColumn(name = "fk_policy_id")
	private Policy policy;
	
	@OneToMany
    @JoinColumn(name="fk_insurancePurchase_id",referencedColumnName = "account_number")
    @JsonIgnoreProperties(value="insuranceAccount")
    List<PaymentDetail> payment;

	public InsuranceAccount() {

	}

	public InsuranceAccount(int accountNumber, String insuranceType, String insuranceScheme, LocalDate dateCreated,
			LocalDate maturityDate, int duration, String premiumType, double totalPremiumAmount,
			double totalPremiumAmountLeft, double installmentAmount, double profitRatio, double sumAssured,
			Customer customer, Policy policy, List<PaymentDetail> payment) {
		super();
		this.accountNumber = accountNumber;
		this.insuranceType = insuranceType;
		this.insuranceScheme = insuranceScheme;
		this.dateCreated = dateCreated;
		this.maturityDate = maturityDate;
		this.duration = duration;
		this.premiumType = premiumType;
		this.totalPremiumAmount = totalPremiumAmount;
		this.totalPremiumAmountLeft = totalPremiumAmountLeft;
		this.installmentAmount = installmentAmount;
		this.profitRatio = profitRatio;
		this.sumAssured = sumAssured;
		this.customer = customer;
		this.policy = policy;
		this.payment = payment;
	}


	public List<PaymentDetail> getPayment() {
		return payment;
	}

	public void setPayment(List<PaymentDetail> payment) {
		this.payment = payment;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public double getTotalPremiumAmountLeft() {
		return totalPremiumAmountLeft;
	}

	public void setTotalPremiumAmountLeft(double totalPremiumAmountLeft) {
		this.totalPremiumAmountLeft = totalPremiumAmountLeft;
	}

	public double getInstallmentAmount() {
		return installmentAmount;
	}

	public void setInstallmentAmount(double installmentAmount) {
		this.installmentAmount = installmentAmount;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Policy getPolicy() {
		return policy;
	}

	public void setPolicy(Policy policy) {
		this.policy = policy;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getInsuranceType() {
		return insuranceType;
	}

	public void setInsuranceType(String insuranceType) {
		this.insuranceType = insuranceType;
	}

	public String getInsuranceScheme() {
		return insuranceScheme;
	}

	public void setInsuranceScheme(String insuranceScheme) {
		this.insuranceScheme = insuranceScheme;
	}

	public LocalDate getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(LocalDate dateCreated) {
		this.dateCreated = dateCreated;
	}

	public LocalDate getMaturityDate() {
		return maturityDate;
	}

	public void setMaturityDate(LocalDate maturityDate) {
		this.maturityDate = maturityDate;
	}

	public String getPremiumType() {
		return premiumType;
	}

	public void setPremiumType(String premiumType) {
		this.premiumType = premiumType;
	}

	public double getTotalPremiumAmount() {
		return totalPremiumAmount;
	}

	public void setTotalPremiumAmount(double totalPremiumAmount) {
		this.totalPremiumAmount = totalPremiumAmount;
	}

	public double getProfitRatio() {
		return profitRatio;
	}

	public void setProfitRatio(double profitRatio) {
		this.profitRatio = profitRatio;
	}

	public double getSumAssured() {
		return sumAssured;
	}

	public void setSumAssured(double sumAssured) {
		this.sumAssured = sumAssured;
	}

}
