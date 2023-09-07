package com.monocept.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.monocept.entity.Employee;
import com.monocept.service.IEmployeeService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1/employee")
public class EmployeeController {

	@Autowired
	private IEmployeeService service;
	
	@PostMapping("/save")
	public Employee save(@RequestBody Employee employee) {
		return service.save(employee);
	}
	
	@GetMapping("/getallemp")
	public List<Employee> getAllAdmin(
			@RequestParam(name = "page", defaultValue = "0") int page,
	        @RequestParam(name = "size", defaultValue = "10") int size
			){
		return service.getAllEmployee(page,size);
	}
	
	@GetMapping("/getid/{id}")
	public Employee getById(@PathVariable int id) {
		return service.getEmployeeById(id);
	}
	
	@GetMapping("/getusername/{username}")
	public Employee getByUsername(@PathVariable String username) {
		return service.getEmployeeByUsername(username);
	}
	
	@PutMapping("/update/{id}")
	public Employee update(@PathVariable int id,@RequestBody Employee employee) {
		return service.update(id, employee);
	}
	
	@PutMapping("/delete/{id}")
	public Employee delete(@PathVariable int id) {
		return service.delete(id);
	}
	
//	@PutMapping("/update")
//	public Employee update(@RequestBody Employee employee) {
//		return service.update(employee);
//	}
}
