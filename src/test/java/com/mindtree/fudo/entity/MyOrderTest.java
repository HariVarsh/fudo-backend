package com.mindtree.fudo.entity;
import static org.junit.Assert.assertEquals;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class MyOrderTest {
	@Test
	public void test() {
		LocalDate date=LocalDate.now();
		MyOrder myOrder = new MyOrder();
		Customer customer=null;
		Restaurant restaurant=null;
		Food food=null;
		List<Food> foodList=new ArrayList<Food>();
		foodList.add(null);
		
		myOrder.setCustomer(customer);
		myOrder.setOrderDate(Date.valueOf(date));
		myOrder.setOrderId(1);
		myOrder.setRating(5);
		myOrder.setRestaurant(restaurant);
		myOrder.setTotalPrice(25);
		myOrder.setFoodList(foodList);
	
		assertEquals(myOrder.getCustomer(), null);
		assertEquals(myOrder.getFoodList(), foodList);
		assertEquals(myOrder.getOrderDate(),Date.valueOf(date) );
		assertEquals(myOrder.getRestaurant(), null);
		assertEquals(myOrder.getTotalPrice(), 25,25);
		assertEquals(myOrder.getOrderId(), 1);
		assertEquals(myOrder.toString().isEmpty(), false);
		assertEquals(myOrder.getRating(), 5);
		
		MyOrder myOrders = new MyOrder(1, Date.valueOf(date),5, 25, customer,  restaurant,foodList,"address");
	
	}
}
