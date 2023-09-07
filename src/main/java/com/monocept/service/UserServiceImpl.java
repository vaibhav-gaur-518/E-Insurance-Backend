package com.monocept.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.monocept.entity.Agent;
import com.monocept.entity.User;
import com.monocept.exception.UserNotFoundException;
import com.monocept.repository.AgentRepository;
import com.monocept.repository.UserRepository;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private AgentRepository agentRepo;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public User getUser(String username) {
		Optional<User> findByUsername = userRepo.findByUsername(username);
		if (!findByUsername.isPresent())
			throw new UserNotFoundException("User with username " + username + " not found");

		return findByUsername.get();
	}

	@Override
	public User getUser(int id) {
		Optional<User> findById = userRepo.findById(id);
		if (!findById.isPresent())
			throw new UserNotFoundException("User with id " + id + " not found");

		return findById.get();
	}

	@Override
	public User saveUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		return userRepo.save(user);
	}

	@Override
	public User getUserDetail(String username) {
		Optional<User> findByUsername = userRepo.findByUsername(username);
		if (!findByUsername.isPresent())
			throw new UserNotFoundException("User with username " + username + " not found");

		return findByUsername.get();
	}

	@Override
	public List<String> getUsernames() {
		List<String> Usernames = new ArrayList<>();
		List<User> findAll = userRepo.findAll();
		for (User u : findAll)
			Usernames.add(u.getUsername());

		return Usernames;
	}

	@Override
	public List<String> getEmails() {
		List<String> Emails = new ArrayList<>();
		List<User> findAll = userRepo.findAll();
		for (User u : findAll) {
			Emails.add(u.getEmail());
		}
		return Emails;
	}

	@Override
	public User updatePassword(int id, User user) {
		Optional<User> findById = userRepo.findById(id);
		if (!findById.isPresent())
			throw new UserNotFoundException("User with userId " + findById.get().getUserId() + " not found");

		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		findById.get().setPassword(user.getPassword());

		return userRepo.save(findById.get());
	}
}
