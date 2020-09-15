package com.mindtree.fudo.service;

import java.util.List;

import javax.validation.Valid;

import com.mindtree.fudo.entity.Food;
import com.mindtree.fudo.exceptions.RecordNotFoundException;

public interface FoodService {
	List<Food> getFoodById(int id) throws RecordNotFoundException;

	String insertFood(Food food) throws RecordNotFoundException;

	String updateStatus(Food food) throws RecordNotFoundException;

	String updateFood(Food food) throws RecordNotFoundException;

	String deleteFood(int foodId) throws RecordNotFoundException;

	List<Food> getFoodByIdAll( int restaurantId)throws RecordNotFoundException;
}
