package com.mindtree.fudo.service.impl;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.dao.DataAccessException;

import com.mindtree.fudo.entity.City;
import com.mindtree.fudo.entity.Cuisine;
import com.mindtree.fudo.entity.Food;
import com.mindtree.fudo.entity.Restaurant;
import com.mindtree.fudo.exceptions.RecordNotFoundException;
import com.mindtree.fudo.repository.FoodRepository;
@RunWith(MockitoJUnitRunner.Silent.class)
public class FoodServiceImplTest {
    @InjectMocks
    FoodServiceImpl foodServiceImpl;
    @Mock
    FoodRepository foodRepository;
	@Test()
	public void test() throws RecordNotFoundException {
		Cuisine cuisine = new Cuisine(1, "north-indian");
		City city = new City(1, "gaya");
		Restaurant restaurant= new Restaurant(1, "yash", "yash@gmail.com", 4356789, "43567", "gaya", "heritage", "gaya",
				"open", "../..", 4.2, city);
		
	Food foodObj = new Food(1,"roti", 4,"open", cuisine,restaurant);
	List<Food> foodList = new ArrayList<Food>();
	List<Food> food = new ArrayList<Food>();
	foodList.add(foodObj);
	
	try {
		Mockito.when(foodRepository.findAll()).thenReturn(foodList);
        
		for (Food a : foodList) {
			
			if (a.getRestaurant().getRestaurantId() == 1 && a.getFoodStatus().equalsIgnoreCase("open")) {
				food.add(a);
				
			}

		}
		assertEquals(food,foodServiceImpl.getFoodById(1));
		
	} catch (DataAccessException e) {
		throw new RecordNotFoundException("no such food is present...data access error", e);
	}
	
	
	}
	
	

	@Test
	public void deleteFood() throws RecordNotFoundException
	{
		Food food=new Food(1, "dfghjk", 20,"open", null, null);
	
		Mockito.when(foodRepository.deleteById(1)).thenReturn(null);
		String s=foodServiceImpl.deleteFood(1);
		assertEquals("success", s);
		
	}
	
	
	@Test
	public void saveFood() throws RecordNotFoundException
	{
		Food food=new Food(1, "dfghjk", 20,"open", null, null);
		Mockito.when(foodRepository.save(food)).thenReturn(food);
		String s=foodServiceImpl.insertFood(food);
		assertEquals("success", s);
	}
	
	
	
	
	
	
	
}
