package com.monocept.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monocept.entity.City;
import com.monocept.entity.InsurancePlan;
import com.monocept.entity.State;
import com.monocept.entity.Status;
import com.monocept.exception.UserNotFoundException;
import com.monocept.repository.CityRepository;
import com.monocept.repository.StateRepository;

@Service
public class IStateServiceImpl implements IStateService{
	
	@Autowired
	StateRepository stateRepo;
	
	@Autowired
	CityRepository cityRepo;

	@Override
	public State save(State state) {
		return stateRepo.save(state);
	}

	@Override
	public List<State> getAllState(int page, int size) {
		return stateRepo.findAll();
	}

	@Override
	public List<City> getAllCity(int page, int size, int stateId) {
		Optional<State> findById = stateRepo.findById(stateId);
		if(!findById.isPresent()) {
			throw new UserNotFoundException("State with id "+findById.get().getId()+" not found");
		}
		return findById.get().getCities();
	}

	@Override
	public State saveCity(int id,City city) {
		 Optional<State> stateOptional = stateRepo.findById(id);
		    if (stateOptional.isPresent()) {
		        State state = stateOptional.get();
		        city.setState(state);
		        state.getCities().add(city);
		        stateRepo.save(state);
		        return state;
		    } else {
		        throw new IllegalArgumentException("State not found with id: " + id);
		    }
	}

	@Override
	public State updateState(int id) {
		Optional<State> findById = stateRepo.findById(id);
		if(!findById.isPresent()) {
			throw new UserNotFoundException("State with id "+findById.get().getId()+" not found");
		}
		State state = findById.get();
	    Status currentStatus = state.getStatus();

	    if (currentStatus == Status.ACTIVE) {
	    	state.setStatus(Status.INACTIVE);
	    } else if (currentStatus == Status.INACTIVE) {
	    	state.setStatus(Status.ACTIVE);
	    }

	    return stateRepo.save(state);
	}

	@Override
	public City updateCity(int id) {
		Optional<City> findById = cityRepo.findById(id);
		if(!findById.isPresent()) {
			throw new UserNotFoundException("City with id "+findById.get().getId()+" not found");
		}
		City city = findById.get();
	    Status currentStatus = city.getStatus();

	    if (currentStatus == Status.ACTIVE) {
	    	city.setStatus(Status.INACTIVE);
	    } else if (currentStatus == Status.INACTIVE) {
	    	city.setStatus(Status.ACTIVE);
	    }

	    return cityRepo.save(city);
	}

	@Override
	public City getCity(int page, int size, int cityId) {
		return cityRepo.findById(cityId).get();
	}

}
