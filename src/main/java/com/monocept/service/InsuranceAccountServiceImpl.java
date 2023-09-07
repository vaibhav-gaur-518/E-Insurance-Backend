package com.monocept.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monocept.entity.Customer;
import com.monocept.entity.InsuranceAccount;
import com.monocept.entity.PaymentDetail;
import com.monocept.entity.Policy;
import com.monocept.entity.User;
import com.monocept.exception.EntityNotFoundException;
import com.monocept.exception.UserNotFoundException;
import com.monocept.repository.CustomerRepository;
import com.monocept.repository.InsuranceAccountRepository;
import com.monocept.repository.PaymentDetailRepository;
import com.monocept.repository.PolicyRepository;
import com.monocept.repository.UserRepository;

@Service
public class InsuranceAccountServiceImpl implements IInsuranceAccountService {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private InsuranceAccountRepository insuranceAccRepo;

	@Autowired
	private PaymentDetailRepository paymentDetailRepo;

	@Autowired
	private CustomerRepository customerRepo;

	@Autowired
	private PolicyRepository policyRepo;

	@Override
	public List<InsuranceAccount> findAll() {
		return insuranceAccRepo.findAll();
	}

	@Override
	public InsuranceAccount save(InsuranceAccount insuranceAcc) {
		return insuranceAccRepo.save(insuranceAcc);
	}

	@Override
	public List<InsuranceAccount> saveAll(List<InsuranceAccount> insuranceAccList) {
		return insuranceAccRepo.saveAll(insuranceAccList);
	}

	@Override
	public InsuranceAccount findById(int insuranceAccId) {
		return insuranceAccRepo.findById(insuranceAccId).get();
	}

	@Override
	public void deleteById(int insuranceAccId) {
		insuranceAccRepo.deleteById(insuranceAccId);

	}

	@Override
	public InsuranceAccount save(String username, int policyId, InsuranceAccount insuranceAccount) {
		Customer customer = customerRepo.findByUsername(username);
		if (customer == null)
			throw new UserNotFoundException("Customer with username " + username + " not found");

		Optional<Policy> findById = policyRepo.findById(policyId);
		if (!findById.isPresent())
			throw new UserNotFoundException("Scheme with id " + policyId + " not found");

		Integer duration = insuranceAccount.getDuration();
		double profitRatio = insuranceAccount.getProfitRatio();
		double totalPremiumAmount = insuranceAccount.getTotalPremiumAmount();
		LocalDate datecreated = insuranceAccount.getDateCreated();
		LocalDate maturitydate = datecreated.plusYears(duration);

		insuranceAccount.setTotalPremiumAmountLeft(totalPremiumAmount);
		insuranceAccount.setSumAssured(totalPremiumAmount + ((profitRatio / 100) * totalPremiumAmount));
		insuranceAccount.setInstallmentAmount(totalPremiumAmount / duration);
		insuranceAccount.setInsuranceType(findById.get().getPlan().getInsuranceType());
		insuranceAccount.setInsuranceScheme(findById.get().getPolicyName());
		insuranceAccount.setMaturityDate(maturitydate);
		insuranceAccount.setCustomer(customer);
		insuranceAccount.setPolicy(findById.get());

		return insuranceAccRepo.save(insuranceAccount);
	}

	@Override
	public List<InsuranceAccount> getByUserName(String username) {
		List<InsuranceAccount> insuranceAccounts = new ArrayList<>();
		Customer findByUsername = customerRepo.findByUsername(username);
		if (findByUsername == null)
			throw new UserNotFoundException("Customer with username " + username + " not found");
		
		int customerId = findByUsername.getCustomerId();
		List<InsuranceAccount> findAll = insuranceAccRepo.findAll();
		for (InsuranceAccount acc : findAll) {
			if (acc.getCustomer().getCustomerId() == customerId) {
				insuranceAccounts.add(acc);
			}
		}
		return insuranceAccounts;
	}

	@Override
	public List<PaymentDetail> payment(int accountNumber, double totalPremiumAmount, double totalPremiumAmountLeft,
			double installmentAmount) {

		if (totalPremiumAmountLeft == 0) {
			throw new UserNotFoundException("All Payment is Done");
		}

		Optional<InsuranceAccount> findById = insuranceAccRepo.findById(accountNumber);
		if (findById.isEmpty()) {
			throw new UserNotFoundException("No Account Found");
		}
		
		findById.get().setTotalPremiumAmountLeft(totalPremiumAmountLeft - installmentAmount);

		PaymentDetail save = paymentDetailRepo.save(new PaymentDetail(installmentAmount, true, findById.get()));
		
		List<PaymentDetail> payment = findById.get().getPayment();
		payment.add(save);
		findById.get().setPayment(payment);
		
		insuranceAccRepo.save(findById.get());

		return findById.get().getPayment();
	}

	@Override
	public List<PaymentDetail> paymentDetails(int accountNumber) {
		Optional<InsuranceAccount> findById = insuranceAccRepo.findById(accountNumber);
		if (findById.isEmpty()) {
			throw new UserNotFoundException("No Transaction Found");
		}
		return findById.get().getPayment();
	}
}