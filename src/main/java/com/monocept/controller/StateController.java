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

import com.monocept.entity.Admin;
import com.monocept.entity.City;
import com.monocept.entity.State;
import com.monocept.service.IAdminService;
import com.monocept.service.IStateService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/state")
public class StateController {
	
	@Autowired
	private IStateService service;
	
	@PostMapping("/save")
	public State save(@RequestBody State state) {
		return service.save(state);
	}
	
	@GetMapping("/getall")
	public List<State> getAllState(
			@RequestParam(name = "page", defaultValue = "0") int page,
	        @RequestParam(name = "size", defaultValue = "10") int size
			){
		return service.getAllState(page,size);
	}
	
	
	@PostMapping("/save/city/{id}")
	public State saveCity(@PathVariable int id,@RequestBody City city) {
		return service.saveCity(id,city);
	}
	
	@GetMapping("/city/{cityId}")
	public City getCity(
			@RequestParam(name = "page", defaultValue = "0") int page,
	        @RequestParam(name = "size", defaultValue = "10") int size,
	        @PathVariable int cityId
			){
		return service.getCity(page,size,cityId);
	}
	
	@GetMapping("/getall/city/{stateId}")
	public List<City> getAllCity(
			@RequestParam(name = "page", defaultValue = "0") int page,
	        @RequestParam(name = "size", defaultValue = "10") int size,
	        @PathVariable int stateId
			){
		return service.getAllCity(page,size,stateId);
	}
	
	@PutMapping("/update/{id}")
	public State updateState(@PathVariable int id) {
		return service.updateState(id);
	}
	
	@PutMapping("/update/city/{id}")
	public City updateCity(@PathVariable int id) {
		return service.updateCity(id);
	}

}
