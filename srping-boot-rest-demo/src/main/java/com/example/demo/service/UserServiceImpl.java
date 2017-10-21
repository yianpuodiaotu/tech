package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
@Service
public class UserServiceImpl implements UserService {

	private List<User> userList = new ArrayList<>();
	@PostConstruct
	public void init(){
		User user = new User();
		user.setId(1L);
		user.setName("userName1");
		userList.add(user);
		user = new User();
		user.setId(2L);
		user.setName("userName2");
		userList.add(user);
	}
	
	@Override
	public List<User> findUsers() {
		return userList;
	}

	@Override
	public User findUserById(Long id) {
		for (User user : userList) {
			if(user.getId().equals(id)){
				return user;
			}
		}
		return null;
	}

	@Override
	public int addUser(User user) {
		userList.add(user);
		return 1;
	}

	@Override
	public int updateUser(User user) {
		for (User temp : userList) {
			if(temp.getId().equals(user.getId())){
				temp.setName(user.getName());
			}
		}
		return 1;
	}

	@Override
	public boolean deleteUser(Long id) {
		User user = null;
		for (User temp : userList) {
			if(temp.getId().equals(id)){
				user = temp;
			}
		}
		if(null != user){
			userList.remove(user);
			return true;
		}
		return false;
	}

}
