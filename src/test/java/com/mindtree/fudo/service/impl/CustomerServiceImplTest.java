package com.mindtree.fudo.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.mindtree.fudo.entity.Customer;
import com.mindtree.fudo.exceptions.RecordAlreadyExistsException;
import com.mindtree.fudo.repository.CustomerRepository;
import com.mindtree.fudo.service.CustomerService;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerServiceImplTest {

	@Autowired
	private CustomerService customerService;
	
	@MockBean
	private CustomerRepository customerRepo;
	
//	testing the getall method
	@Test
	public void testGetAll() 
	{
//		List<Customer> customers= new ArrayList<Customer>();
		
		Mockito.when(customerRepo.findAll()).thenReturn(Stream.of(new Customer(1, "Sathish", "Chandregowda",9999999999L, "sathishMalemane@gmail.com",
				"PaliParth123", "Akhila123", 50)).collect(Collectors.toList()));
		
		assertEquals(1,customerService.getall().size());
	}

//	testing the registering the data
	@Test
	public void addCustomer() throws RecordAlreadyExistsException
	{
//		List<Customer> customers= new ArrayList<Customer>();

		Customer customerentity=new Customer(1, "Sathish", "Chandregowda",9999999999L, "sathishMalemane@gmail.com",
				"PaliParth123", "Akhila123", 50);
		
		Mockito.when(customerRepo.save(customerentity)).thenReturn(customerentity);
		
		assertEquals(customerentity,customerService.setSignup(customerentity));
		
		assertNotEquals(new Customer(), customerService.setSignup(customerentity));
		
		assertNotNull(customerService.setSignup(customerentity));
		
		assertSame(customerentity,customerService.setSignup(customerentity));

		
//		customerService.setSignup(customerentity);
	}
}
