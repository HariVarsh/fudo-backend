package com.mindtree.fudo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.mindtree.fudo.entity.Food;
import com.mindtree.fudo.exceptions.RecordNotFoundException;
import com.mindtree.fudo.repository.FoodRepository;
import com.mindtree.fudo.service.FoodService;

@Service
public class FoodServiceImpl implements FoodService {
	@Autowired
	FoodRepository foodRepository;

	/**
	 * methode to get list of foods present in particular restaurant
	 */
	@Override
	public List<Food> getFoodById(int id) throws RecordNotFoundException {
		List<Food> foodList = new ArrayList<Food>();
		List<Food> food = new ArrayList<Food>();
		try {
			// get all food from database
			foodList = foodRepository.findAll();
			// filtering restaurants for given restaurant id
			for (Food a : foodList) {
				if (a.getRestaurant().getRestaurantId() == id && a.getFoodStatus().equalsIgnoreCase("open")) {
					food.add(a);
				}

			}

		}
		// to catch exception
		catch (DataAccessException e) {
			throw new RecordNotFoundException("no such food is present...data access error", e);
		}
		// returning list of food
		return food;
	}

	@Override
	public String insertFood(Food food) throws RecordNotFoundException {
		try {
		foodRepository.save(food);
		}
		catch(DataAccessException e) {
			throw new RecordNotFoundException("No such food is there to insert");
		}
		return "success";
	}
			
	public String updateStatus(Food food) throws RecordNotFoundException {
		Food foodTemp = new Food();
		try {
			foodTemp = foodRepository.findById(food.getFoodId()).get();
			foodTemp.setFoodStatus(food.getFoodStatus());
			foodRepository.save(foodTemp);

		} catch (DataAccessException e) {
			throw new RecordNotFoundException("No such food is there to update status");
		}
		return "success";
	}

	@Override
	public String updateFood(Food food) throws RecordNotFoundException {

		Food foodTemp = new Food();
		try {
			foodTemp = foodRepository.findById(food.getFoodId()).get();
			foodTemp.setFoodName(food.getFoodName());
			foodTemp.setFoodType(food.getFoodType());
			foodTemp.setPrice(food.getPrice());

			foodRepository.save(foodTemp);

		} catch (DataAccessException e) {
			throw new RecordNotFoundException("No such food is there to update");
		}
		return "success";
	}

	@Override
	public String deleteFood(int foodId) throws RecordNotFoundException {
	
			Integer i=foodRepository.deleteById(foodId);
			
			if(i!=null) {
		
			throw new RecordNotFoundException("No such food is there to delete");
		}
		return "success";
	}

	@Override
	public List<Food> getFoodByIdAll(int restaurantId) throws RecordNotFoundException {
		List<Food> foodList = new ArrayList<Food>();
		List<Food> food = new ArrayList<Food>();
		
		try {
			// get all food from database
			foodList = foodRepository.findAll();
			
			for (Food a : foodList) {
				if (a.getRestaurant().getRestaurantId() == restaurantId) {
					food.add(a);
				}

			}
		
		}
		// to catch exception
		catch (DataAccessException e) {
			throw new RecordNotFoundException("no such food is present...data access error", e);
		}
		// returning list of food
		return food;
	
	}
	

}
