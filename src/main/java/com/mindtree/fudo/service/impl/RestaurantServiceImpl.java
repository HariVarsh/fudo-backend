package com.mindtree.fudo.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.mindtree.fudo.entity.Food;
import com.mindtree.fudo.entity.Restaurant;
import com.mindtree.fudo.exceptions.RecordNotFoundException;
import com.mindtree.fudo.repository.FoodRepository;
import com.mindtree.fudo.repository.RestaurantRepository;
import com.mindtree.fudo.service.RestaurantService;

@Service
public class RestaurantServiceImpl implements RestaurantService {
	@Autowired
	RestaurantRepository restaurantRepository;
	@Autowired
	FoodRepository FoodRepository;

	/**
	 * to get a set of restaurants present in particular city
	 */
	@Override
	public Set<Restaurant> getRestaurant(int cityId) throws RecordNotFoundException {
		List<Restaurant> restaurants = new ArrayList<Restaurant>();
		List<Food> foods = new ArrayList<Food>();
		Set<Restaurant> restaurantWithFood = new HashSet<Restaurant>();
		try {
			/**
			 * to get all restaurant present in database
			 */
			restaurants = restaurantRepository.findAll();
			/**
			 * to get all food present in database
			 */
			foods = FoodRepository.findAll();

			/**
			 * filtering restaurants which has no food ,should not be added to the list of
			 * restaurant to be displayed on ui and adding restaurants to restaurant list on
			 * the basis of its city id
			 */
			for (Restaurant restaurant : restaurants) {
				for (Food food : foods) {
					if (restaurant.getRestaurantId() == food.getRestaurant().getRestaurantId()
							&& restaurant.getCity().getCityId() == cityId) {
						restaurantWithFood.add(restaurant);
					}
				}
			}

		}
		/**
		 * to get catch data access exception
		 */
		catch (DataAccessException e) {
			throw new RecordNotFoundException("Error in finding restaurant", e);
		}

		return restaurantWithFood;

	}

}
