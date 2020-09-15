package com.mindtree.fudo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.mindtree.fudo.entity.Customer;
import com.mindtree.fudo.exceptions.RecordAlreadyExistsException;
import com.mindtree.fudo.repository.CustomerRepository;
import com.mindtree.fudo.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService
{
	@Autowired
	CustomerRepository customerRepo;

//	implementing the registration of user
	@Override
	public Customer setSignup(Customer customer) throws RecordAlreadyExistsException
	{
		try
		{
			return customerRepo.save(customer);
		}
		catch(DataAccessException e)
		{
			throw new RecordAlreadyExistsException("Their was an error while fetching the data");
		}
	}
	
//	getting all the registered user details

	@Override
	public List<Customer> getall()
	{
		return customerRepo.findAll();
		
	}

}
