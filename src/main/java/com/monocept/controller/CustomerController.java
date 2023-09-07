package com.monocept.controller;

import java.util.List;
import java.util.Set;

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

import com.monocept.entity.Admin;
import com.monocept.entity.Agent;
import com.monocept.entity.Customer;
import com.monocept.exception.CustomerNotFoundException;
import com.monocept.service.ICustomerService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1/customer")
public class CustomerController {
	@Autowired
	private ICustomerService customerService;

	@GetMapping("/getall")
	public List<Customer> findAll() {
		List<Customer> customers = customerService.findAll();
		return customers;
	}
	
	@GetMapping("/{agentId}")
	public List<Customer> getByAgentId(@PathVariable int agentId) {
		return customerService.getByAgentId(agentId);
	}
	
	@GetMapping("/get/{username}")
	public Set<Customer> getByUsername(@PathVariable String username) {
		return customerService.getCustomerByUsername(username);
	}
	
	@GetMapping("/getcustomer/{username}")
	public Customer getCustomerByUsername(@PathVariable String username) {
		return customerService.getCustomerByUsernames(username);
	}

	@PostMapping("/save")
	public Customer saveCustomer(@RequestBody Customer customer) {
		return customerService.save(customer);
	}

	@PostMapping("/saveall")
	public List<Customer> saveAllCustomers(@RequestBody List<Customer> customerList) {
		return customerService.saveAll(customerList);
	}

	@GetMapping("/getbyid/{custId}")
	public Customer findById(@PathVariable int custId) {
		Customer customer = null;
		customer = customerService.findById(custId);
		if (customer == null) {
			throw new CustomerNotFoundException("Customer with id " + custId + " is not found");
		}
		return customer;
	}
	
	@PutMapping("/delete/{id}")
	public Customer delete(@PathVariable int id) {
		return customerService.delete(id);
	}
	
	@PutMapping("/update/{id}")
	public Customer update(@PathVariable int id,@RequestBody Customer customer) {
		return customerService.update(id, customer);
	}

	@DeleteMapping("/delete/{custId}")
	public void deleteCustomerById(@PathVariable int custId) {
		customerService.deleteById(custId);
	}

//	@PutMapping("/customerid/{custId}/document/{docId}")
//	public Customer assignDocToCustomer(@PathVariable int custId, @PathVariable int docId) {
//		return customerService.addDocToCustomer(custId, docId);
//	}
//
//	@PutMapping("/customerid/{custId}/insuranceacc/{insuranceAccNo}")
//	public Customer assignInsuranceToCustomer(@PathVariable int custId, @PathVariable int insuranceAccNo) {
//		return customerService.addInsuranceToCustomer(custId, insuranceAccNo);
//	}

}
