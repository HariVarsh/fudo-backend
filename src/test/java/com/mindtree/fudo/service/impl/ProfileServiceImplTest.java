package com.mindtree.fudo.service.impl;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.dao.DataAccessException;

import com.mindtree.fudo.entity.Customer;
import com.mindtree.fudo.exceptions.InvalidDataException;
import com.mindtree.fudo.exceptions.RecordNotFoundException;
import com.mindtree.fudo.repository.CustomerRepository;
@RunWith(MockitoJUnitRunner.Silent.class)
public class ProfileServiceImplTest {

	@InjectMocks
	ProfileServiceImpl profileServiceImpl;
	@Mock
	CustomerRepository customerRepository;
	
	@Test()
	public void testGetProfile() throws RecordNotFoundException {
		int custId=1;
		
		Customer customerobject = new Customer(1, "Himanshu", "Choudhary", 9876543210L, "1510himanshu@gmail.com",
				"Abcd123", "Him123", 60);
		Optional<Customer> customer = Optional.of(customerobject);
				
		try {
			//Optional<Customer> customer = customerRepository.findById(custId);
			when(customerRepository.findById(custId)).thenReturn(customer);
			if (!customer.isPresent()) {
				throw new RecordNotFoundException("there is no customer with given id");
			}
			assertEquals(customer, profileServiceImpl.getProfile(custId));
		} catch (DataAccessException e) {
			throw new RecordNotFoundException("Error in fetching customer", e);
		}
	}

	@Test
	public void testUpdateProfile() throws InvalidDataException {
		Customer customer = new Customer(1, "Himanshu", "Choudhary", 9876543210L, "1510himanshu@gmail.com",
				"Abcd123", "Him123", 60);
		try {
			List<Customer> customers = new ArrayList<>();
			customers.add(customer);
              when(customerRepository.findAll()).thenReturn(customers);
			List<Customer> customerOfId = customers.stream().filter(i -> i.getCustomerId() == customer.getCustomerId())
					.collect(Collectors.toList());

			for (Customer cust : customerOfId) {

				cust.setCustomerEmail(customer.getCustomerEmail());
				cust.setCustomerId(customer.getCustomerId());
				cust.setCustomerPhone(customer.getCustomerPhone());
				cust.setFirstName(customer.getFirstName());
				cust.setLastName(customer.getLastName());
				cust.setPassword(customer.getPassword());
				cust.setPoints(customer.getPoints());
				cust.setReferralCode(customer.getReferralCode());
				customerRepository.save(cust);

			}
            
			assertEquals(customer,profileServiceImpl.updateProfile(customer));
			assertNotEquals(new Customer(),profileServiceImpl.updateProfile(customer) );
			assertNotNull(profileServiceImpl.updateProfile(customer));
			assertSame(customer,profileServiceImpl.updateProfile(customer));
			
			
		} catch (DataAccessException e) {
			throw new InvalidDataException("Error in updating customer", e);
		}
	}

}
