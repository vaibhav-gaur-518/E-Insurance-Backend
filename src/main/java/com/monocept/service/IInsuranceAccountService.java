package com.monocept.service;

import java.util.List;

import com.monocept.entity.InsuranceAccount;
import com.monocept.entity.PaymentDetail;

public interface IInsuranceAccountService {

	List<InsuranceAccount> findAll();

	InsuranceAccount save(InsuranceAccount insuranceAcc);

	List<InsuranceAccount> saveAll(List<InsuranceAccount> insuranceAccList);

	InsuranceAccount findById(int insuranceAccId);

	void deleteById(int insuranceAccId);

	InsuranceAccount save(String customerid, int policyId, InsuranceAccount insuranceAccount);

	List<InsuranceAccount> getByUserName(String username);

	List<PaymentDetail> payment(int accountNumber, double totalPremiumAmount, double totalPremiumAmountLeft,
			double installmentAmount);

	List<PaymentDetail> paymentDetails(int accountNumber);

}
