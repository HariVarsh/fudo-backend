package com.mindtree.fudo.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.mindtree.fudo.dto.RestaurantDTO;
@RunWith(SpringJUnit4ClassRunner.class)
public class DisplayRestaurantControllerTest extends DisplayRestaurantController {

	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext webApplicationContext;

	@InjectMocks
	private DisplayRestaurantController displayRestaurantController;
	
	@Before
	public void setUp() throws Exception{
//		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	
		mockMvc=MockMvcBuilders.standaloneSetup(displayRestaurantController).build();
	}
	
	@Test
	public void test() throws Exception {
		mockMvc.perform(get("/employee")).andExpect(status().isOk());
		RestaurantDTO restaurantDTO= new RestaurantDTO();
//	mockMvc.perform(get("/fudo/restaurant/1"))
//	.andExpect(status().isOk());
//	.andExpect(MockMvcResultMatchers.content().contentType(restaurantDTO.js));
	}

}
