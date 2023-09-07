package com.monocept.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class City {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;

	private Status status;

	@ManyToOne
	private State state;

	public City() {
		super();
	}

	public City(int id, String name, Status status, State state) {
		super();
		this.id = id;
		this.name = name;
		this.status = status;
		this.state = state;
	}
	

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

}
