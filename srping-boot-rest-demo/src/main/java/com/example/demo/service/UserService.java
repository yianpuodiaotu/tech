package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.User;

public interface UserService {
	List<User> findUsers();
	User findUserById(Long id);
	int addUser(User user);
	int updateUser(User user);
	boolean deleteUser(Long id);
}
