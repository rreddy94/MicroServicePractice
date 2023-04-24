package com.example.demo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import com.example.demo.entity.UserEntity;
import com.example.demo.model.UserDto;
import com.example.demo.repository.UserRepository;
import com.example.demo.services.UsersServiceImplementation;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class UserServiceTest 
{
	
	@MockBean
	private UserRepository repository;
	
	@InjectMocks
	private UsersServiceImplementation userService;
	
	@Test
	public void testUserService() {
		
	    UserEntity entity = new UserEntity();
		entity.setEmail("user1@gmail.com");
		entity.setEncryptedPassword("abcd");
		entity.setFirstName("user");
		entity.setLastName("check");
		entity.setUserId("abcde");
		
		Mockito.when(repository.save(entity)).thenReturn(entity);
		UserDto userDetails = new UserDto("user1@gmail.com","user","check","abcd");
		
		UserDto response = userService.registerUser(userDetails);
		System.out.println(response.getEmail() + " " + response.getUserId() + " " + response.getEncryptedPassword());
		
		assertThat(response).isNotNull();
		assertNotNull(response.getUserId());
		assertNotNull(response.getEncryptedPassword());
		
	}

}