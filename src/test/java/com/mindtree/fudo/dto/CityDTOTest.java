package com.mindtree.fudo.dto;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.mindtree.fudo.entity.City;
import com.mindtree.fudo.entity.Restaurant;

public class CityDTOTest {
	@Test
	public void test() {
CityDTO cityDto=new CityDTO();
City city=new City(1,"Chennai");
cityDto.setCityId(1);
cityDto.setCityName("Chennai");
Restaurant restaurant = new Restaurant(1, "owner", "email", 213456789,
		"asdfghjkl", "gaya", "heritage", "gaya",
		"open", "imgUrl", 4.2,
		 city);
List<Restaurant> restaurantList=new ArrayList();
restaurantList.add(restaurant);
cityDto.setRestaurantList(restaurantList);
assertEquals(cityDto.getCityId(),1);
assertEquals(cityDto.getCityName(),"Chennai");
assertEquals(cityDto.getRestaurantList().size(),1);
}
	@Test
	public void testForConstructor()
	{
CityDTO cityDto=new CityDTO(1,"Chennai");
	assertEquals(cityDto.getCityId(),1);
	assertEquals(cityDto.getCityName(),"Chennai");
}

	
	
	

}