package com.mindtree.fudo.dto;

import static org.junit.Assert.*;

import org.junit.Test;

import com.mindtree.fudo.entity.Cuisine;
import com.mindtree.fudo.entity.Food;
import com.mindtree.fudo.entity.Restaurant;

public class FoodDTOTest {

	@Test
	public void test() {
		FoodDTO food = new FoodDTO();
		
		Cuisine cuisine = null;
		Restaurant rest = null;
		food.setCuisine(cuisine);
		food.setFoodId(1);
		food.setFoodName("chicken");
		food.setFoodStatus("available");
		food.setPrice(90);
		food.setRestaurant(rest);
		food.setFoodType("veg");

		assertEquals(food.getFoodId(), 1);
		assertEquals(food.getCuisine(), null);
		assertEquals(food.getFoodName(), "chicken");
		assertEquals(food.getFoodStatus(), "available");

		assertEquals(food.getRestaurant(), null);
		assertEquals(food.toString().isEmpty(), false);
		assertEquals(food.getPrice(), 90, 90);
		assertEquals(food.getFoodType(), "veg");
	}
	
	@Test
	public void testForConstructor()
	{
		Cuisine cuisine = null;
		Restaurant rest = null;
		FoodDTO food= new FoodDTO(1, "chicken", 90,"available", cuisine,  rest);
		assertEquals(food.getFoodId(), 1);
		assertEquals(food.getCuisine(), null);
		assertEquals(food.getFoodName(), "chicken");
		assertEquals(food.getFoodStatus(), "available");

		assertEquals(food.getRestaurant(), null);
		assertEquals(food.toString().isEmpty(), false);
		assertEquals(food.getPrice(), 90, 90);
	}

}
