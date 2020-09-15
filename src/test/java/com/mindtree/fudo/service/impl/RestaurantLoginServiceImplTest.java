package com.mindtree.fudo.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mail.javamail.JavaMailSender;
import com.mindtree.fudo.entity.City;
import com.mindtree.fudo.entity.Restaurant;
import com.mindtree.fudo.exceptions.MyApplicationException;
import com.mindtree.fudo.exceptions.NotValidEmail;
import com.mindtree.fudo.exceptions.NotaValidPassword;
import com.mindtree.fudo.exceptions.RecordNotFoundException;
import com.mindtree.fudo.repository.RestaurantRepository;

@RunWith(MockitoJUnitRunner.Silent.class)
public class RestaurantLoginServiceImplTest {

	@InjectMocks
	private RestaurantLoginServiceImpl restaurant_Login_Service_Impl;

	@Mock
	private RestaurantRepository restaurant_repository;

	@Mock
	private JavaMailSender jms;

	@Test
	public void Update_restaurant_password() throws RecordNotFoundException, NotaValidPassword {

		String email = "himanshu.choudhary2@mindtree.com";
		String password = "Himan12";
		City city = new City(1, "Hyderabad");
		long num = 9900773322L;
		List<Restaurant> restaurantList = new ArrayList<Restaurant>();
		Restaurant restaurantobject = new Restaurant(1, "vivek", "himanshu.choudhary2@mindtree.com", num, "Qwerty123",
				"restaurantAddress", "restaurantName", "restaurantCity", "restaurantStatus", "imageUrl", 3.5, city);
		restaurantList.add(restaurantobject);
		when(restaurant_repository.findAll()).thenReturn(restaurantList);
		assertEquals("password updated successfully",
				restaurant_Login_Service_Impl.UpdateRestaurantpassword(email, password));
	}

	@Test
	public void dislayAll() throws MyApplicationException {
		
		City city = new City(1, "Hyderabad");
		long num = 9900773322L;
		List<Restaurant> restaurantList = new ArrayList<Restaurant>();
		Restaurant restaurantobject = new Restaurant(1, "vivek", "himanshu.choudhary2@mindtree.com", num, "Qwerty123",
				"restaurantAddress", "restaurantName", "restaurantCity", "restaurantStatus", "imageUrl", 3.5, city);
		restaurantList.add(restaurantobject);
		when(restaurant_repository.findAll()).thenReturn(restaurantList);
		assertEquals(1, restaurant_Login_Service_Impl.displayAll().size());
	}

	@Test
	public void sendOTP() throws NotValidEmail, RecordNotFoundException {
		String otp = "1234";
		String email = "himanshu.choudhary2@mindtree.com";
		City city = new City(1, "Hyderabad");
		long num = 9900773322L;
		List<Restaurant> restaurantList = new ArrayList<Restaurant>();
		Restaurant restaurantobject = new Restaurant(1, "vivek", "himanshu.choudhary2@mindtree.com", num, "Qwerty123",
				"restaurantAddress", "restaurantName", "restaurantCity", "restaurantStatus", "imageUrl", 3.5, city);
		restaurantList.add(restaurantobject);
		when(restaurant_repository.findAll()).thenReturn(restaurantList);
		assertEquals("otp send successfully", restaurant_Login_Service_Impl.sendOTP(otp, email));
	}

}
