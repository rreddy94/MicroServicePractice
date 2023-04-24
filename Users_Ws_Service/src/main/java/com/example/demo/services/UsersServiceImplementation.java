package com.example.demo.services;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.UserEntity;
import com.example.demo.model.UserDto;
import com.example.demo.repository.UserRepository;

@Service
public class UsersServiceImplementation implements UserService {

	private UserRepository userRepository;
	
	@Autowired
	public UsersServiceImplementation(UserRepository userRepository) {
		
		this.userRepository = userRepository;
	}

	@Override
	public UserDto registerUser(UserDto userDetails) {
		
		
		userDetails.setUserId(UUID.randomUUID().toString());
		userDetails.setEncryptedPassword(UUID.randomUUID().toString());
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		UserEntity userEntity = mapper.map(userDetails, UserEntity.class);
		
		userRepository.save(userEntity);
		return userDetails;
	}

}