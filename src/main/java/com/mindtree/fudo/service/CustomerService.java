package com.mindtree.fudo.service;

import java.util.List;

import com.mindtree.fudo.entity.Customer;
import com.mindtree.fudo.exceptions.MyApplicationException;
import com.mindtree.fudo.exceptions.RecordAlreadyExistsException;

public interface CustomerService 
{
	public Customer setSignup(Customer customer) throws RecordAlreadyExistsException;
	
	public List<Customer> getall();

}
