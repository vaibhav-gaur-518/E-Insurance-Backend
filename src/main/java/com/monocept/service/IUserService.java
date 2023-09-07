package com.monocept.service;


import java.util.List;

import com.monocept.entity.Agent;
import com.monocept.entity.User;

public interface IUserService{

	public User getUser(String username);
	public User getUser(int id);
	public User saveUser(User user);
	public User getUserDetail(String username);
	public java.util.List<String> getUsernames();
	public List<String> getEmails();
	public User updatePassword(int id,User user);
}
