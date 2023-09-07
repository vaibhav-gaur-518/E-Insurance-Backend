package com.monocept.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.monocept.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{

	@Query("select a from Customer a where a.user.username = :username")
	Customer findByUsername(String username);

}
