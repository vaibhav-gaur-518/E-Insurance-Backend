package com.monocept.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.monocept.entity.Employee;
import com.monocept.entity.Status;
import com.monocept.exception.UserNotFoundException;
import com.monocept.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

	@Autowired
	private EmployeeRepository empRepo;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public Employee save(Employee employee) {
		employee.getUser().setPassword(bCryptPasswordEncoder.encode(employee.getUser().getPassword()));
		return empRepo.save(employee);
	}

	@Override
	public List<Employee> getAllEmployee(int page, int size) {
		return empRepo.findAll();
	}

	@Override
	public Employee getEmployeeById(int id) {
		Optional<Employee> employee = empRepo.findById(id);
		if (!employee.isPresent())
			throw new UserNotFoundException("Employee with id" + id + " not found");

		return employee.get();
	}

	@Override
	public Employee getEmployeeByUsername(String username) {
		Employee employee = empRepo.findByUsername(username);
		if (employee == null)
			throw new UserNotFoundException("Employee with username " + username + " not found");
		return employee;
	}

	@Override
	public Employee update(int id, Employee employee) {
		Optional<Employee> employeeById = empRepo.findById(id);
		if (!employeeById.isPresent())
			throw new UserNotFoundException("Employee with id" + employee.getEmployeeId() + " not found");

		employeeById.get().getUser().setName(employee.getUser().getName());
		employeeById.get().getUser().setEmail(employee.getUser().getEmail());
		employeeById.get().getUser().setAddress(employee.getUser().getAddress());
		employeeById.get().getUser().setStatus(employee.getUser().getStatus());

		return empRepo.save(employeeById.get());
	}

	@Override
	public Employee delete(int id) {
		Optional<Employee> employeeById = empRepo.findById(id);
		if (!employeeById.isPresent())
			throw new UserNotFoundException("Employee with id  not found");

		employeeById.get().getUser().setStatus(Status.INACTIVE);
		return empRepo.save(employeeById.get());
	}

}
