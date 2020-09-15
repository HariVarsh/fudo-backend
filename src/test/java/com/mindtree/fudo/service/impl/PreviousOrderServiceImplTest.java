package com.mindtree.fudo.service.impl;

import static org.junit.Assert.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.mindtree.fudo.entity.City;
import com.mindtree.fudo.entity.Customer;
import com.mindtree.fudo.entity.Food;
import com.mindtree.fudo.entity.MyOrder;
import com.mindtree.fudo.entity.Restaurant;
import com.mindtree.fudo.exceptions.MyApplicationException;
import com.mindtree.fudo.exceptions.RecordNotFoundException;
import com.mindtree.fudo.repository.FoodRepository;
import com.mindtree.fudo.repository.OrderRepository;

@RunWith(MockitoJUnitRunner.Silent.class)
public class PreviousOrderServiceImplTest {
	@InjectMocks
	PreviousOrderServiceImpl previousOrdersService;
	@Mock
	OrderRepository orderRepository;
	@Mock
	FoodRepository foodRepository;
	@Test
	public void test() throws MyApplicationException{
		
		List<MyOrder> orders= new ArrayList<MyOrder>();
		List<Food> foodList=new ArrayList<>();
		foodList=foodRepository.findAll();
		int customerId=234;
		City city=new City(1010,"bhubaneswar");
		Customer customer=new Customer(234,"vani","madhavi",9553587681l,"vani@gmail.com","vani453","ursvani",50);
		Restaurant restaurant =new Restaurant(11,"owner1","owner1@gmail.com",674358473,"rest5345","patia","eats","bhubaneswar","open","ntng",4,city);
		List<MyOrder> orderWithData=new  ArrayList<MyOrder>();
		LocalDate date=LocalDate.now();
		
		assertNotEquals(3,orderWithData.size());
		
		MyOrder myOrder=new MyOrder(51,Date.valueOf(date),4,170.0,customer,restaurant,foodList,"Patia");
		orders.add(myOrder);
		Mockito.when(orderRepository.findAll()).thenReturn(orders);
		Mockito.when(foodRepository.findAll()).thenReturn(foodList);
		for (MyOrder c : orders) {
			if (c.getCustomer().getCustomerId() == customerId) {
				orderWithData.add(c);
			}
		}
		assertEquals(orderWithData,previousOrdersService.getAllOrders(234));
		assertNotSame(orderWithData, previousOrdersService.getAllOrders(234));
	}
}
