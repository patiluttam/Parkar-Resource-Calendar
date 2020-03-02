package com.resource.calendar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Strings;
import com.resource.calendar.model.User;
import com.resource.calendar.model.UserLogin;
import com.resource.calendar.model.UserRole;
import com.resource.calendar.service.UserRoleService;
import com.resource.calendar.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/")
public class UserController {
	
	@Autowired 
	private UserService userService;
	
	@Autowired
	private UserRoleService userRoleService;

	@GetMapping("login")
	public String loginPage() {
		return "login.html";
	}
	
	@PostMapping("login")
	public ResponseEntity<?> login(@RequestBody UserLogin userLogin) {
		log.info("User Credentials :{}, {}", userLogin.getUsername(), userLogin.getPassword());
		ResponseEntity<?> response = null;
		if(Strings.isNullOrEmpty(userLogin.getUsername()) || Strings.isNullOrEmpty(userLogin.getPassword())) {
			 return new ResponseEntity<String>("Invalid Username & Password", HttpStatus.BAD_REQUEST);
		}		
		//check user is exist or not with the unique username
		User user = userService.getUserByUserName(userLogin.getUsername());
		log.info("User Id:{}", user.getId());
		if(user != null) {
				response = ResponseEntity.ok().body("User_DashBoard.html");
		}else {
			response = new ResponseEntity<String>("Bad Username & Password", HttpStatus.BAD_REQUEST);
		}
		return response ;
	}
	
	@PostMapping("registration")
	public ResponseEntity<?> registration(@RequestBody User userdata) {
			
		User existUser = userService.getUserByUserName(userdata.getUsername());
		
		if(existUser == null) {
			User user = userService.saveUser(userdata);
			if(user != null) {
				return new ResponseEntity<User>(userdata, HttpStatus.CREATED);
			}
		}
		return new ResponseEntity<String>("Username already exists.", HttpStatus.BAD_REQUEST);
	}

}
