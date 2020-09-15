package com.mindtree.fudo.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import com.mindtree.fudo.entity.Customer;
import com.mindtree.fudo.exceptions.NotValidEmail;
import com.mindtree.fudo.exceptions.NotaValidPassword;
import com.mindtree.fudo.exceptions.RecordNotFoundException;
import com.mindtree.fudo.repository.CustomerRepository;

@RunWith(MockitoJUnitRunner.Silent.class)

public class CustomerLoginServiceImplTest {

	@InjectMocks
	CustomerLoginServiceImpl customerservice;

	@Mock
	CustomerRepository customerRepository;

	@Mock
	private JavaMailSender jms;

	@Test
	public void UpdateCustomerpassword() throws RecordNotFoundException, NotaValidPassword {

		String email="1510himanshu@gmail.com";
		String password="Himan12";
		List<Customer> customerList = new ArrayList<Customer>();
		Customer customerobject = new Customer(1, "Himanshu", "Choudhary", 9876543210L, "1510himanshu@gmail.com",
				"Abcd123", "Him123", 60);
		customerList.add(customerobject);
		when(customerRepository.findAll()).thenReturn(customerList);
		assertEquals("password updated successfully",customerservice.UpdateCustomerpassword(email, password));
		
	}

	@Test
	public void dislayAll() {

		List<Customer> customerList = new ArrayList<Customer>();
		Customer customerobject = new Customer(1, "Himanshu", "Choudhary", 9876543210L, "abc@xyz.com", "Abcd123",
				"Him123", 60);
		customerList.add(customerobject);
		when(customerRepository.findAll()).thenReturn(customerList);
		assertEquals(1, customerservice.displayAll().size());

	}

	@Test
	public void sendOTP() throws NotValidEmail, RecordNotFoundException {
		String otp = "1234";
		String email = "1510himanshu@gmail.com";
		List<Customer> customerList = new ArrayList<Customer>();
		Customer customerobject = new Customer(1, "Himanshu", "Choudhary", 9876543210L, "1510himanshu@gmail.com",
				"Abcd123", "Him123", 60);
		customerList.add(customerobject);
		when(customerRepository.findAll()).thenReturn(customerList);
		assertEquals("otp send successfully", customerservice.sendOTP(otp, email));
	}

}
