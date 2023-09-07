package com.monocept.service;

import java.util.List;

import com.monocept.entity.InsurancePlan;
import com.monocept.entity.Policy;


public interface IInsurancePlanService {

	public InsurancePlan save(InsurancePlan insurancePlan);
	public List<InsurancePlan> getAllInsurancePlan(int page,int size);
	public InsurancePlan getInsurancePlanById(int id);
	public InsurancePlan update(int id);
	public List<Policy> getByInsuranceId(int insuranceId);
}
