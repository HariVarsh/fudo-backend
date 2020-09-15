package com.mindtree.fudo.service.impl;

import static org.junit.Assert.assertEquals;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.mindtree.fudo.entity.Cart;
import com.mindtree.fudo.entity.Cuisine;
import com.mindtree.fudo.entity.Customer;
import com.mindtree.fudo.entity.Food;
import com.mindtree.fudo.entity.MyOrder;
import com.mindtree.fudo.entity.Restaurant;
import com.mindtree.fudo.exceptions.MyApplicationException;
import com.mindtree.fudo.exceptions.RecordNotFoundException;
import com.mindtree.fudo.repository.CartRepository;
import com.mindtree.fudo.repository.CustomerRepository;
import com.mindtree.fudo.repository.FoodRepository;
import com.mindtree.fudo.repository.OrderRepository;
import com.mindtree.fudo.repository.RestaurantRepository;

@RunWith(MockitoJUnitRunner.Silent.class)
public class OrderServiceImplTest {

	@InjectMocks
	OrderServiceImpl orderServiceImpl;
	@Mock
	OrderRepository orderRepository;
	@Mock
	CustomerRepository customerRepository;
	@Mock
	FoodRepository foodRepository;
	@Mock
	RestaurantRepository restaurantRepository;
	@Mock
	CartRepository cartRepository;

	@Test
	public void placeOrder() {

		List<Food> foodList = new ArrayList<Food>();
		foodList.add(null);
		LocalDate date = LocalDate.now();
		MyOrder orders = new MyOrder(1, Date.valueOf(date), 5, 1000, null, null, foodList,
				"addres");

		try {

			orderServiceImpl.placeOrder(orders);
		} catch (MyApplicationException e) {
			assertEquals("this address is invalid", e.getMessage());
		}
	}

	@Test
	public void placeOrderEmailExceptionTest() {
		Customer customer = new Customer(1, "hari", "bala", 123, "harivarsh1993@gmail.com", "pass", "hari123", 12);
		Customer customer1 = new Customer();
		List<Customer> cusList = new ArrayList<>();
		cusList.add(customer);

		List<Food> foodList = new ArrayList<Food>();
		foodList.add(null); 
		LocalDate date = LocalDate.now();
		customer1.setCustomerEmail("h@gmail.com");
		MyOrder orders = new MyOrder(1, Date.valueOf(date), 5, 1000, customer1, null, foodList, "addressdjncdcnmdcndcnjdcnjdjdcndmcnmcnmcncnjnjcnmcnxmc");
		try {
			Mockito.when(customerRepository.findAll()).thenReturn(cusList);
			System.out.println(customerRepository.findAll());

			orderServiceImpl.placeOrder(orders);
		} catch (MyApplicationException e) {
			assertEquals("this customer doesnot exists", e.getMessage());
		} 

	}

	@Test
	public void placeOrderRestaurantException() {
		Customer customer = new Customer(1, "hari", "bala", 123, "harivarsh1993@gmail.com", "pass", "hari123", 12);

		List<Customer> cusList = new ArrayList<>();
		cusList.add(customer);
		Restaurant restaurant = new Restaurant();
		Restaurant restaurant1 = new Restaurant();
		restaurant1.setRestaurantId(2);

		restaurant.setRestaurantId(1);
		List<Restaurant> resList = new ArrayList<>();
		resList.add(restaurant);
		MyOrder orders = new MyOrder(0, null, 5, 1000, customer, restaurant1, null, "addressncxcbxcxcnxmnxmxnmxnxnxn");
		try {
			Mockito.when(customerRepository.findAll()).thenReturn(cusList);
			Mockito.when(restaurantRepository.findAll()).thenReturn(resList);

			orderServiceImpl.placeOrder(orders);
		} catch (MyApplicationException e) {

			assertEquals("this restaurant doesn't exist", e.getMessage());
		}

	}

	@Test
	public void deleteFromCartByIdEmailException() {
		String customerEmail = "h@gmail.com";
		int foodId = 1;
		Customer customer = new Customer(1, "hari", "bala", 123, "harivarsh1993@gmail.com", "pass", "hari123", 12);
		List<Customer> cusList = new ArrayList<>();
		cusList.add(customer);
		try {
			Mockito.when(customerRepository.findAll()).thenReturn(cusList);
			orderServiceImpl.deleteFromCartById(customerEmail, foodId);
		} catch (MyApplicationException e) {
			assertEquals("this customer doesnot exists", e.getMessage());
		}
	}

	@Test
	public void deleteFromCartByIdException() {
		String customerEmail = "harivarsh1993@gmail.com";
		int foodId = 1;

		Customer customer = new Customer(1, "hari", "bala", 123, "harivarsh1993@gmail.com", "pass", "hari123", 12);
		List<Customer> cusList = new ArrayList<>();
		cusList.add(customer);
		Cuisine cusine = null;
		Restaurant restaurant = null;
		Food food = new Food(2, "foodName", 100, "avail", cusine, restaurant);
		List<Food> foodList = new ArrayList<>();
		try {
			Mockito.when(customerRepository.findAll()).thenReturn(cusList);
			Mockito.when(foodRepository.findAll()).thenReturn(foodList);
			orderServiceImpl.deleteFromCartById(customerEmail, foodId);
		} catch (MyApplicationException e) {
			assertEquals("this foodId doesnot exists", e.getMessage());
		}
	}

	@Test
	public void deleteAll() {
		String customerEmail = "h@gmail.com";
		Customer customer = new Customer(1, "hari", "bala", 123, "harivarsh1993@gmail.com", "pass", "hari123", 12);
		List<Customer> cusList = new ArrayList<>();
		cusList.add(customer);
		try {
			Mockito.when(customerRepository.findAll()).thenReturn(cusList);
			orderServiceImpl.deleteAll(customerEmail);
		} catch (MyApplicationException e) {
			assertEquals("this customer doesnot exists", e.getMessage());
		}

	}

	@Test
	public void displayCart() {
		String customerEmail = "h@gmail.com";
		Cart cart = new Cart(1, "h@gmail.com", "chicken", 1, 2, 1, 100);
		List<Cart> cList = new ArrayList<>();
		cList.add(cart);
		int flag = 0;
		Mockito.when(cartRepository.findAll()).thenReturn(cList);
		orderServiceImpl.displayCart(customerEmail);

	}

	@Test
	public void getCoins() {
		String customerEmail = "h@gmail.com";
		Customer customer = new Customer(1, "hari", "bala", 123, "harivarsh1993@gmail.com", "pass", "hari123", 12);
		List<Customer> cusList = new ArrayList<>();
		cusList.add(customer);
		try {
			Mockito.when(customerRepository.findAll()).thenReturn(cusList);

			orderServiceImpl.getCoins(customerEmail);
		} catch (MyApplicationException e) {
			assertEquals("this customer doesnot exists", e.getMessage());
		}

	}
	@Test
	public void updateCoins() {
		String customerEmail = "h@gmail.com";
		Customer customer = new Customer(1, "hari", "bala", 123, "harivarsh1993@gmail.com", "pass", "hari123", 12);
		List<Customer> cusList = new ArrayList<>();
		cusList.add(customer);
		try {
			Mockito.when(customerRepository.findAll()).thenReturn(cusList);

			orderServiceImpl.updateCoins(customerEmail,0);
		} catch (MyApplicationException e) {
			assertEquals("this customer doesnot exists", e.getMessage());
		}

	}

}
