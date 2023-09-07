package com.monocept.service;

import java.util.List;
import java.util.Set;

import com.monocept.entity.Admin;
import com.monocept.entity.Agent;
import com.monocept.entity.Customer;

public interface ICustomerService {

	Customer save(Customer customer);

	List<Customer> findAll();

	List<Customer> saveAll(List<Customer> customerList);

	Customer findById(int custId);

	void deleteById(int custId);

	Customer delete(int id);

	Customer update(int id, Customer customer);

	Set<Customer> getCustomerByUsername(String username);

	List<Customer> getByAgentId(int agentId);

	Customer getCustomerByUsernames(String username);


}
