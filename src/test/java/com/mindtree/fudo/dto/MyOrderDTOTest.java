package com.mindtree.fudo.dto;

import static org.junit.Assert.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.mindtree.fudo.entity.City;
import com.mindtree.fudo.entity.Customer;
import com.mindtree.fudo.entity.Food;
import com.mindtree.fudo.entity.Restaurant;

public class MyOrderDTOTest {
	

	@Test
	public void test() {
		
		MyOrderDTO orderDto=new MyOrderDTO();
		Customer customer=new Customer();
		Restaurant restaurant=new Restaurant();
		List<Food> foodList=new ArrayList<>();
		City city=new City();
		LocalDate date=LocalDate.now();
		
		city.setCityId(1010);
		city.setCityName("Bhubaneswar");
		
		customer.setCustomerId(234);
		customer.setCustomerEmail("vani@gmail.com");
		customer.setCustomerPhone(9553587681l);
		customer.setFirstName("vani");
		customer.setLastName("madhavi");
		customer.setPassword("vani453");
		customer.setPoints(50);
		customer.setReferralCode("ursvani");
		
		restaurant.setRestaurantId(11);
		restaurant.setRestaurantName("eats");
		restaurant.setOwnerName("owner1");
		restaurant.setOwnerEmail("owner1@gmail.com");
		restaurant.setOwnerPhone(674358473);
		restaurant.setImageUrl("ntng");
		restaurant.setRestaurantAddress("patia");
		restaurant.setRestaurantCity("bhubaneswar");
		restaurant.setRestaurantPassword("rest5345");
		restaurant.setAverageRating(4);
		restaurant.setCity(city);
		
		orderDto.setOrderId(51);
		orderDto.setOrderDate(Date.valueOf(date));
		orderDto.setRating(4);
		orderDto.setTotalPrice(170.0);
		orderDto.setAddress("Patia");
		orderDto.setCustomer(customer);
		orderDto.setRestaurant(restaurant);
		orderDto.setFoodList(foodList);
		
		assertEquals(orderDto.getOrderId(), 51);
		assertEquals(orderDto.getOrderDate(), Date.valueOf(date));
		assertEquals(orderDto.getRating(), 4);
		assertEquals(orderDto.getTotalPrice(), 170.0,170);
		assertEquals(orderDto.getAddress(),"Patia");
		assertEquals(orderDto.getCustomer(),customer);
		assertEquals(orderDto.getFoodList(), foodList);
		assertEquals(orderDto.getRestaurant(), restaurant);
	}

}
