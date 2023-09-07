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

import com.monocept.entity.InsurancePlan;
import com.monocept.entity.Policy;
import com.monocept.service.IInsurancePlanService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1/insuranceplan")
public class InsurancePlanController {

	@Autowired
	private IInsurancePlanService service;
	
	@PostMapping("/save")
	public InsurancePlan save(@RequestBody InsurancePlan insurancePlan) {
		return service.save(insurancePlan);
	}
	
	@GetMapping("/getall")
	public List<InsurancePlan> getAllInsurancePlan(
			@RequestParam(name = "page", defaultValue = "0") int page,
	        @RequestParam(name = "size", defaultValue = "10") int size
			){
		return service.getAllInsurancePlan(page,size);
	}
	
	@GetMapping("/getid/{id}")
	public InsurancePlan getById(@PathVariable int id) {
		return service.getInsurancePlanById(id);
	}
	
	@GetMapping("/getByInsuranceid/{insuranceId}")
	public List<Policy> getByInsuranceId(@PathVariable int insuranceId) {
		return service.getByInsuranceId(insuranceId);
	}
	
	
	@PutMapping("/update/{id}")
	public InsurancePlan update(@PathVariable int id) {
		return service.update(id);
	}
}
