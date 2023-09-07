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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.monocept.entity.InsuranceAccount;
import com.monocept.entity.PaymentDetail;
import com.monocept.exception.CustomerNotFoundException;
import com.monocept.repository.InsuranceAccountRepository;
import com.monocept.service.IInsuranceAccountService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1/insuranceapp")
public class InsuranceAccountController {

	@Autowired
	private IInsuranceAccountService insuranceAccService;

	@GetMapping("/getallaccounts")
	public List<InsuranceAccount> findAll() {
		List<InsuranceAccount> insuranceAccounts = insuranceAccService.findAll();
		return insuranceAccounts;
	}

	@GetMapping("/get/{username}")
	public List<InsuranceAccount> getByUserName(@PathVariable String username) {
		return insuranceAccService.getByUserName(username);
	}

	@PostMapping("/save")
	public ResponseEntity<InsuranceAccount> saveInsuranceAccount(@RequestBody InsuranceAccount insuranceAcc) {
		insuranceAccService.save(insuranceAcc);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@PostMapping("/saveall")
	public List<InsuranceAccount> saveAllInsuranceAccounts(@RequestBody List<InsuranceAccount> insuranceAccList) {
		return insuranceAccService.saveAll(insuranceAccList);
	}

	@GetMapping("/getbyid/{insuranceAccId}")
	public InsuranceAccount findById(@PathVariable int insuranceAccId) {
		InsuranceAccount insuranceAcc = null;
		insuranceAcc = insuranceAccService.findById(insuranceAccId);
		if (insuranceAcc == null) {
			throw new CustomerNotFoundException("Insurance Account with id " + insuranceAccId + " is not found");
		}
		return insuranceAcc;
	}

	@DeleteMapping("/delete/{insuranceAccId}")
	public void deleteInsuranceAccIdById(@PathVariable int insuranceAccId) {
		insuranceAccService.deleteById(insuranceAccId);
	}

	@PostMapping("/buypolicy/{username}/{policyId}")
	public InsuranceAccount save(@PathVariable String username, @PathVariable int policyId,
			@RequestBody InsuranceAccount insuranceAccount) {
		return insuranceAccService.save(username, policyId, insuranceAccount);
	}

	@PutMapping("/payment/{accountNumber}/{totalPremiumAmount}/{totalPremiumAmountLeft}/{installmentAmount}")
	public List<PaymentDetail> payment(@PathVariable int accountNumber, @PathVariable double totalPremiumAmount, @PathVariable double totalPremiumAmountLeft,
			@PathVariable double installmentAmount) {
		return insuranceAccService.payment(accountNumber,totalPremiumAmount,totalPremiumAmountLeft,installmentAmount);
	}
	
	@GetMapping("/payment/details/{accountNumber}")
	public List<PaymentDetail> paymentDetails(@PathVariable int accountNumber) {
		return insuranceAccService.paymentDetails(accountNumber);
	}

}