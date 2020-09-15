package com.mindtree.fudo.dto;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import com.mindtree.fudo.entity.City;
import com.mindtree.fudo.entity.Food;

public class RestaurantDTOTest {

	@Test
	public void test() {
		City city = null;
		RestaurantDTO restaurant = new RestaurantDTO();
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
	@Test
	public void testForConstructor() {
		City city = null;
		RestaurantDTO restaurant = new RestaurantDTO(1, "owner", "email", 213456789,
				"asdfghjkl", "gaya", "heritage", "gaya",
				"open", "imgUrl", 4.2,
				 city);
	
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
