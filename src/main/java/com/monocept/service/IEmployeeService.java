package com.monocept.service;

import java.util.List;

import com.monocept.entity.Employee;


public interface IEmployeeService {

	public Employee save(Employee employee);
	public List<Employee> getAllEmployee(int page,int size);
	public Employee getEmployeeById(int id);
	public Employee getEmployeeByUsername(String username);
	public Employee update(int id,Employee admin);
	public Employee delete(int id);
	
}
