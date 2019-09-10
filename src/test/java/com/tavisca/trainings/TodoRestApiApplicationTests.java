package com.tavisca.trainings;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import com.tavisca.trainings.controllers.RootController;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TodoRestApiApplicationTests {
	
	@Autowired
	private MockMvc mockMvc;
	
	@InjectMocks
	private RootController rootController;
	
	@Before
	public void setUp() {
		MockMvcBuilders.standaloneSetup(rootController).build();
	}

	@Test
	public void apiRespondsToGetRequestReceived() throws Exception{
		
		mockMvc.perform(
				MockMvcRequestBuilders.get("/todoapi/v1/")
				)
		
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.content().string("Welcome to REST API of TODO APP"));
	}
	
	@Test
	public void apiRespondsWithJsonToGetRequestReceived() throws Exception{
		
		mockMvc.perform(
				MockMvcRequestBuilders.get("/todoapi/v1/suggestions/")
				)
		
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$.statusCode", Matchers.is(HttpStatus.OK.toString())))
		.andExpect(MockMvcResultMatchers.jsonPath("$.*", Matchers.hasSize(3)));
	}
	
	@Test
	public void apiRespondsBadRequestWhenInvalidDataIsInputInPostRequest() throws Exception{
		
		mockMvc.perform(
				MockMvcRequestBuilders.post("/todoapi/v1/suggestions/").accept(MediaType.APPLICATION_JSON)
				)
		
//		.andExpect(MockMvcResultMatchers.status().isBadRequest())
		.andExpect(MockMvcResultMatchers.jsonPath("$.statusCode", Matchers.is(HttpStatus.BAD_REQUEST.toString())))
		.andExpect(MockMvcResultMatchers.jsonPath("$.*", Matchers.hasSize(3)));
		
	}
	
	@Test
	public void apiRespondsWithAllTodoInGetRequest() throws Exception{
		
		mockMvc.perform(
				MockMvcRequestBuilders.get("/todoapi/v1/todo/")
				)
		
//		.andExpect(MockMvcResultMatchers.status().isBadRequest())
		.andExpect(MockMvcResultMatchers.jsonPath("$.data[0].*", Matchers.hasSize(4)))
		.andExpect(MockMvcResultMatchers.jsonPath("$.statusCode", Matchers.is(HttpStatus.OK.toString())))
		.andExpect(MockMvcResultMatchers.jsonPath("$.*", Matchers.hasSize(3)));
		
	}
		

}
