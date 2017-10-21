package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;

@EnableAutoConfiguration
@RestController
public class UserController {
	@Autowired
	UserService userService;

	@GetMapping("/users")
	public ResponseEntity<List<User>> findAllUsers() {
		List<User> userList = userService.findUsers();
		if (userList.size() == 0) {
			return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<User>>(userList, HttpStatus.OK);
	}

	@PostMapping("/users")
	public ResponseEntity<User> saveUser(@RequestBody User user) {
		int result = userService.addUser(user);
		if (result == 1) {
			return new ResponseEntity<User>(user, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/users/{id}")
	public ResponseEntity<User> findUser(@PathVariable("id") Long id) {
		User user = userService.findUserById(id);
		if (null == user) {
			return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@PutMapping("/users/{id}")
	public ResponseEntity<User> updatUser(@PathVariable("id") Long id, @RequestBody User user) {
		User currentUser = userService.findUserById(id);

		if (null == currentUser) {
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}

		currentUser.setId(id);
		currentUser.setName(user.getName());

		userService.updateUser(currentUser);
		return new ResponseEntity<User>(currentUser, HttpStatus.OK);
	}

	@DeleteMapping(value = "/users/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable Long id) {
		boolean result = userService.deleteUser(id);
		if (result) {
			return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
	}
}
