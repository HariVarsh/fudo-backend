package com.mindtree.fudo.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.mindtree.fudo.entity.City;
import com.mindtree.fudo.entity.Cuisine;
import com.mindtree.fudo.entity.Food;
import com.mindtree.fudo.entity.Restaurant;
import com.mindtree.fudo.exceptions.RecordNotFoundException;
import com.mindtree.fudo.repository.FoodRepository;
import com.mindtree.fudo.repository.RestaurantRepository;

@RunWith(MockitoJUnitRunner.Silent.class)
public class RestaurantServiceImplTest {

	@Rule
	public ExpectedException thrown= ExpectedException.none();
	@InjectMocks
	RestaurantServiceImpl restaurantServiceImpl;
	@Mock
	RestaurantRepository restaurantRepository;
	@Mock
	FoodRepository foodRepository;

	@Test()
	public void test() throws RecordNotFoundException {
		
		
		List<Restaurant> restaurants = new ArrayList<Restaurant>();
		List<Food> foods = new ArrayList<>();
		City city = new City(1, "gaya");
		Cuisine cuisine = new Cuisine();
		Set<Restaurant> restaurantWithFood = new HashSet<Restaurant>();
		
		assertEquals(0, restaurantWithFood.size());
		Restaurant rest = new Restaurant(1, "yash", "yash@gmail.com", 4356789, "43567", "gaya", "heritage", "gaya",
				"available", "../..", 4.2, city);
		Food foodOne = new Food(1, "chicken", 100, "available", cuisine, rest);
		foods.add(foodOne);
		restaurants.add(rest);
		when(restaurantRepository.findAll()).thenReturn(restaurants);
		when(foodRepository.findAll()).thenReturn(foods);
		for (Restaurant restaurant : restaurants) {
			for (Food food : foods) {
				if (restaurant.getRestaurantId() == food.getRestaurant().getRestaurantId()
						&& restaurant.getCity().getCityId() == 1) {
					restaurantWithFood.add(restaurant);
				}
			}
		}

	
		assertEquals(restaurantWithFood,restaurantServiceImpl.getRestaurant(1));


		
		
	   
	}
}
