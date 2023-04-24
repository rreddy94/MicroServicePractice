package com.example.demo.services;

import com.example.demo.model.UserDto;
import com.example.demo.model.UserRequestModel;


public interface UserService {
	
	public UserDto registerUser(UserDto userDetails);
}
