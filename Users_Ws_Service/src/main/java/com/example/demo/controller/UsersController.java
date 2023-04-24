package com.example.demo.controller;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.UserDto;
import com.example.demo.model.UserRequestModel;
import com.example.demo.model.UserResponseModel;
import com.example.demo.services.UserService;

@RestController
@RequestMapping("/users")
public class UsersController {
	
	private Environment environment;
	private UserService userService;
	
	@Autowired
	public UsersController(Environment environment, UserService userService) {
		this.environment = environment;
		this.userService = userService;
	
	}

	@GetMapping("/status/check")
	public String status() 
	{
		return "Working on the port " + environment.getProperty("server.port");
	}

	@PostMapping("/register")
	public ResponseEntity<UserResponseModel> registerUser(@RequestBody UserRequestModel userRequest)
	{
		ModelMapper mapper = new ModelMapper();
		
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		UserDto userRequestDetails = mapper.map(userRequest, UserDto.class);
		UserDto userResponseDetails = userService.registerUser(userRequestDetails);
		
		UserResponseModel userResponse = mapper.map(userResponseDetails, UserResponseModel.class);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
	}
	
}