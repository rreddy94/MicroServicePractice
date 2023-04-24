package com.example.demo.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.demo.entity.UserEntity;



@DataJpaTest
public class UserRepositoryTest {

	@Autowired
	private UserRepository userRespository;
	
	
	@Test
	public void testRegister()
	{
		UserEntity entity = new UserEntity();
		entity.setEmail("user1@gmail.com");
		entity.setEncryptedPassword("abcd");
		entity.setFirstName("user");
		entity.setLastName("check");
		entity.setUserId("abcde");
		
		UserEntity savedUser = userRespository.save(entity);
			
		assertThat(savedUser.getId()).isNotNull();
		assertEquals("user1@gmail.com", savedUser.getEmail());
		assertNotNull(savedUser);
	}
}