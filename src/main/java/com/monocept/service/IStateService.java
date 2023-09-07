package com.monocept.service;

import java.util.List;

import com.monocept.entity.City;
import com.monocept.entity.State;

public interface IStateService {

	State save(State state);

	List<State> getAllState(int page, int size);

	List<City> getAllCity(int page, int size, int stateId);

	State saveCity(int id,City city);

	State updateState(int id);

	City updateCity(int id);

	City getCity(int page, int size, int cityId);

}
