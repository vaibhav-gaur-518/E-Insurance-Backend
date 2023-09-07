package com.monocept.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monocept.entity.InsurancePlan;
import com.monocept.entity.Policy;
import com.monocept.entity.Status;
import com.monocept.exception.UserNotFoundException;
import com.monocept.repository.InsurancePlanRepository;

@Service
public class InsurancePlanServiceImpl implements IInsurancePlanService {
	
	@Autowired
	private InsurancePlanRepository planRepo;

	@Override
	public InsurancePlan save(InsurancePlan insurancePlan) {
		return planRepo.save(insurancePlan);
	}

	@Override
	public List<InsurancePlan> getAllInsurancePlan(int page, int size) {
		return planRepo.findAll();
	}

	@Override
	public InsurancePlan getInsurancePlanById(int id) {
		Optional<InsurancePlan> insurance = planRepo.findById(id);
		if(!insurance.isPresent()) {
			throw new UserNotFoundException("Insurance with id "+id+" not found");
		}
		return insurance.get();
	}

	@Override
	public InsurancePlan update(int id) {
		Optional<InsurancePlan> insurance = planRepo.findById(id);
		if(!insurance.isPresent()) {
			throw new UserNotFoundException("Insurance with id "+insurance.get().getInsuranceId()+" not found");
		}
		InsurancePlan insurancePlan = insurance.get();
	    Status currentStatus = insurancePlan.getStatus();

	    if (currentStatus == Status.ACTIVE) {
	        insurancePlan.setStatus(Status.INACTIVE);
	    } else if (currentStatus == Status.INACTIVE) {
	        insurancePlan.setStatus(Status.ACTIVE);
	    }

	    return planRepo.save(insurancePlan);
	}
	
	@Override
	public List<Policy> getByInsuranceId(int insuranceId) {
		Optional<InsurancePlan> findById = planRepo.findById(insuranceId);
		if(!findById.isPresent()) {
			throw new UserNotFoundException("Insurance with id "+findById.get().getInsuranceId()+" not found");
		}
		return findById.get().getPolicies();
	}
}