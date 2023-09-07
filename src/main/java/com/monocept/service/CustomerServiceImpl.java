package com.monocept.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.monocept.entity.Admin;
import com.monocept.entity.Agent;
import com.monocept.entity.Customer;
import com.monocept.entity.Document;
import com.monocept.entity.InsuranceAccount;
import com.monocept.entity.Status;
import com.monocept.exception.UserNotFoundException;
import com.monocept.repository.AgentRepository;
import com.monocept.repository.CustomerRepository;
import com.monocept.repository.FileRepository;
import com.monocept.repository.InsuranceAccountRepository;

@Service
public class CustomerServiceImpl implements ICustomerService {

	@Autowired
	CustomerRepository customerRepo;

	@Autowired
	AgentRepository agentRepo;

	@Autowired
	FileRepository fileRepo;

	@Autowired
	InsuranceAccountRepository insuranceAccRepo;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public Customer save(Customer customer) {
		Optional<Agent> findById = agentRepo.findById(customer.getAgentId());
		if (!findById.isPresent())
			throw new UserNotFoundException("Agent with id " + customer.getAgentId() + " not found");

		customer.getUser().setPassword(bCryptPasswordEncoder.encode(customer.getUser().getPassword()));
		findById.get().getCustomers().add(customer);
		agentRepo.save(findById.get());
		customerRepo.save(customer);
		return customer;
	}

	@Override
	public List<Customer> findAll() {
		return customerRepo.findAll();
	}

	@Override
	public List<Customer> saveAll(List<Customer> customerList) {
		return customerRepo.saveAll(customerList);
	}

	@Override
	public Customer findById(int custId) {
		return customerRepo.findById(custId).get();
	}

	@Override
	public void deleteById(int custId) {
		customerRepo.deleteById(custId);
	}

	@Override
	public Customer delete(int id) {
		Optional<Customer> customerById = customerRepo.findById(id);
		if (!customerById.isPresent())
			throw new UserNotFoundException("Customer with id " + id + " not found");

		customerById.get().getUser().setStatus(Status.INACTIVE);
		return customerRepo.save(customerById.get());
	}

	@Override
	public Customer update(int id, Customer customer) {
		Optional<Customer> customerById = customerRepo.findById(id);
		if (!customerById.isPresent())
			throw new UserNotFoundException("Customer with id" + customer.getAgentId() + " not found");

		customerById.get().getUser().setName(customer.getUser().getName());
		customerById.get().getUser().setEmail(customer.getUser().getEmail());
		customerById.get().getUser().setAddress(customer.getUser().getAddress());
		customerById.get().getUser().setStatus(customer.getUser().getStatus());

		return customerRepo.save(customerById.get());
	}

	@Override
	public Set<Customer> getCustomerByUsername(String username) {
		Agent agent = agentRepo.findByUsername(username);
		if (agent == null)
			throw new UserNotFoundException("Agent with username" + username + " not found");
		return agent.getCustomers();

	}

	@Override
	public List<Customer> getByAgentId(int agentId) {
		List<Customer> agentCustomers = new ArrayList<>();
		List<Customer> findAll = customerRepo.findAll();

		for (Customer c : findAll) {
			if (c.getAgentId() == agentId) {
				System.out.println(c.getAgentId() + agentId);
				agentCustomers.add(c);
			}
		}

		return agentCustomers;
	}

	@Override
	public Customer getCustomerByUsernames(String username) {
		Customer customer = customerRepo.findByUsername(username);
		if (customer == null)
			throw new UserNotFoundException("Customer with username" + username + " not found");
		return customer;
	}
}

//@Override
//public Customer addDocToCustomer(int custId, int docId) {
//	Customer customer = customerRepo.findById(custId).get();
//	Document document = fileRepo.findById((long) docId).get();
//	Set<Document> documents = customer.getDocuments();
//	documents.add(document);
//	customer.setDocuments(documents);
//
//	return customerRepo.save(customer);
//}
//
//@Override
//public Customer addInsuranceToCustomer(int custId, int insuranceId) {
//	Customer customer = customerRepo.findById(custId).get();
//	InsuranceAccount insuranceAcc = insuranceAccRepo.findById(insuranceId).get();
//	Set<InsuranceAccount> insuranceAccounts = customer.getInsuranceAccounts();
//	insuranceAccounts.add(insuranceAcc);
//	customer.setInsuranceAccounts(insuranceAccounts);
//
//	return customerRepo.save(customer);
//}