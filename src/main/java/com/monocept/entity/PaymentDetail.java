package com.monocept.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class PaymentDetail {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="payment_id")
    private int paymentId;

    private double amount;

    private boolean status;

    @ManyToOne
    @JoinColumn(name ="fk_insurancePurchase_id")
    @JsonIgnoreProperties(value= "payment")
    private InsuranceAccount insuranceAccount;
    
    

	public PaymentDetail() {
		super();
	}

	public PaymentDetail(int paymentId, double amount, boolean status, InsuranceAccount insuranceAccount) {
		super();
		this.paymentId = paymentId;
		this.amount = amount;
		this.status = status;
		this.insuranceAccount = insuranceAccount;
	}

	public PaymentDetail(double amount, boolean status, InsuranceAccount insuranceAccount) {
		super();
		this.amount = amount;
		this.status = status;
		this.insuranceAccount = insuranceAccount;
	}

	public int getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public InsuranceAccount getInsuranceAccount() {
		return insuranceAccount;
	}

	public void setInsuranceAccount(InsuranceAccount insuranceAccount) {
		this.insuranceAccount = insuranceAccount;
	}
}