package com.monocept.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.monocept.entity.Agent;
import com.monocept.entity.User;
import com.monocept.service.IUserService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/user")
public class UserController {

	@Autowired
	private IUserService service;
	
	@GetMapping("/details")
	public User getUserDetail(Principal principal) {
		return service.getUserDetail(principal.getName());
	}
	
	@GetMapping("/username")
	public List<String> getUsernames() {
		return service.getUsernames();
	}
	
	@GetMapping("/email")
	public List<String> getEmails() {
		return service.getEmails();
	}
	
	@PostMapping("/save")
	public User save(@RequestBody User user) {
		return service.saveUser(user);
	}
	
	@PutMapping("/password/{id}")
	public User updatePassword(@PathVariable int id,@RequestBody User user) {
		return service.updatePassword(id,user);
	}
	
//	@GetMapping("/get-all")
//	public List<Admin> getAllAdmin(
//			@RequestParam(name = "page", defaultValue = "0") int page,
//	        @RequestParam(name = "size", defaultValue = "10") int size
//			){
//		return service.getAllAdmin(page,size);
//	}
//	
//	
//	@GetMapping("/get-username/{username}")
//	public Admin getByUsername(@PathVariable String username) {
//		return service.getAdminByUsername(username);
//	}
//	
//	@PutMapping("/update")
//	public Admin update(@RequestBody Admin admin) {
//		return service.update(admin);
//	}
}
