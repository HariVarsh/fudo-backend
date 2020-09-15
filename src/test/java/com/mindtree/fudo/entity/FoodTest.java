package com.mindtree.fudo.entity;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.mockito.Mockito;

public class FoodTest {

	@Test
	public void test() {
		Food food = new Food();
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

}
