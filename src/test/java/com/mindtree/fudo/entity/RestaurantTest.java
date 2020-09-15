package com.mindtree.fudo.entity;

import static org.junit.Assert.*;

import org.junit.Test;

public class RestaurantTest {

	@Test
	public void test() {
		City city = null;
		Restaurant restaurant = new Restaurant();
		restaurant.setAverageRating(4.2);
		restaurant.setCity(city);
		restaurant.setImageUrl("imgUrl");
		restaurant.setOwnerEmail("email");
		restaurant.setOwnerName("owner");
		restaurant.setOwnerPhone(213456789);
		restaurant.setRestaurantAddress("gaya");
		restaurant.setRestaurantCity("gaya");
		restaurant.setRestaurantId(1);
		restaurant.setRestaurantName("heritage");
		restaurant.setRestaurantPassword("asdfghjkl");
		restaurant.setRestaurantStatus("open");
		assertEquals(restaurant.getAverageRating(), 4.2, 4);
		assertEquals(restaurant.getCity(), null);
		assertEquals(restaurant.getImageUrl(), "imgUrl");
		assertEquals(restaurant.getOwnerName(), "owner");
		assertEquals(restaurant.getOwnerEmail(), "email");
		assertEquals(restaurant.getOwnerPhone(), 213456789);
		assertEquals(restaurant.getRestaurantAddress(), "gaya");
		assertEquals(restaurant.getRestaurantCity(), "gaya");
		assertEquals(restaurant.getRestaurantId(), 1);
		assertEquals(restaurant.getRestaurantName(), "heritage");
		assertEquals(restaurant.getRestaurantPassword(), "asdfghjkl");
		assertEquals(restaurant.getRestaurantStatus(), "open");
		assertEquals(restaurant.toString().isEmpty(), false);

	}

}
