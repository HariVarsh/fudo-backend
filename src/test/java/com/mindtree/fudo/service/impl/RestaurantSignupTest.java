package com.mindtree.fudo.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.mindtree.fudo.entity.City;
import com.mindtree.fudo.entity.Restaurant;
import com.mindtree.fudo.exceptions.MyApplicationException;
import com.mindtree.fudo.exceptions.RecordAlreadyExistsException;
import com.mindtree.fudo.repository.CityRepository;
import com.mindtree.fudo.repository.RestaurantSignupRepository;
import com.mindtree.fudo.service.CityService;
import com.mindtree.fudo.service.RestaurantSignup;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RestaurantSignupTest {

	@Autowired
	private RestaurantSignup restaurantService;

	@MockBean
	private CityService cityService;

	@Autowired
	private CityRepository cityRepo;

	@MockBean
	private RestaurantSignupRepository restaurantSignupRepo;

	@Test
	public void getAllTest() {

		City city = new City(1, "Hyderabad");

		long num = 9900773322L;

		when(restaurantSignupRepo.findAll()).thenReturn(Stream
				.of(new Restaurant(1, "vivek", "vivek@gmail.com", num, "Qwerty123", "restaurantAddress",
						"restaurantName", "restaurantCity", "restaurantStatus", "imageUrl", 3.5, city))
				.collect(Collectors.toList()));

		assertEquals(1, restaurantService.getAll().size());
		assertNotNull(restaurantService.getAll().get(0));
		assertNotSame(
				new Restaurant(1, "vivek", "vivek@gmail.com", num, "Qwerty123", "restaurantAddress", "restaurantName",
						"restaurantCity", "restaurantStatus", "imageUrl", 3.5, city),
				new Restaurant(1, "vicky", "vivek@gmail.com", num, "Qwerty123", "restaurantAddress", "restaurantName",
						"restaurantCity", "restaurantStatus", "imageUrl", 3.5, city));
	}

	@Test
	public void addRestaurantTest() throws MyApplicationException {

		// Under Construction
		City city1 = new City(1, "Hyderabad");

		City city2 = new City(2, "Bangalore");

		List<City> cityList = new ArrayList<City>();

		cityList.add(city1);
		cityList.add(city2);

		long num = 9900773322L;

		Restaurant restaurantObj1 = new Restaurant(1, "vivek", "vivek@gmail.com", num, "Qwerty123", "restaurantAddress",
				"restaurantName", "Hyderabad", "restaurantStatus", "imageUrl", 3.5, city1);

		Restaurant restaurantObj2 = new Restaurant(1, "vivek", "vivek@gmail.com", num, "Qwerty123", "restaurantAddress",
				"restaurantName", "Hyderabad", "restaurantStatus", "imageUrl", 3.5, city2);

		Restaurant restaurantObj3 = new Restaurant(1, "vivek", "vivek@gmail.com", num, "Qwerty123", "restaurantAddress",
				"restaurantName", "Bangalore", "restaurantStatus", "imageUrl", 3.5, new City(2, "Bangalore"));

		List<Restaurant> resList = new ArrayList<Restaurant>();

		resList.add(restaurantObj1);
		resList.add(restaurantObj2);

		Mockito.when(cityService.getAllCity()).thenReturn(cityList);

		restaurantSignupRepo.save(restaurantObj3);

		assertEquals(restaurantObj3, restaurantService.addRestaurant(restaurantObj3));

	}

}
