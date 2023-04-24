package com.example.demo.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.model.UserDto;
import com.example.demo.services.UsersServiceImplementation;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers=UsersController.class)
public class UserControllerTest 
{	
	@Autowired
	private MockMvc mockMvc; 
	
	@MockBean
	private UsersServiceImplementation service;
	
	@Autowired
	private ObjectMapper mapper;
	
	@Test
	public void testGetService()
	{
		try
		{
		String url = "/users/register";
		UserDto userDetails = new UserDto("user1@gmail.com","user","check","abcd");
		Mockito.when(service.registerUser(Mockito.any(UserDto.class))).thenReturn(userDetails);
		
		
		mockMvc.perform(post(url)
				.contentType("application/json")
				.content(mapper.writeValueAsString(userDetails)))
		       .andExpect(status().isCreated())
		       .andExpect(jsonPath("$.email").value("user1@gmail.com"))
			   .andExpect(jsonPath("$.firstName").value("user"))
			   .andExpect(jsonPath("$.lastName").value("check"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
