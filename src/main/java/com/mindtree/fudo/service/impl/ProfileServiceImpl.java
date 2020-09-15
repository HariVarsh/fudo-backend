package com.mindtree.fudo.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.mindtree.fudo.entity.Customer;
import com.mindtree.fudo.exceptions.InvalidDataException;
import com.mindtree.fudo.exceptions.RecordNotFoundException;
import com.mindtree.fudo.repository.CustomerRepository;
import com.mindtree.fudo.service.ProfileService;

@Service
public class ProfileServiceImpl implements ProfileService {

	@Autowired
	CustomerRepository customerRepository;

	@Override
	public Optional<Customer> getProfile(int custId) throws RecordNotFoundException {

		try {
			Optional<Customer> customer = customerRepository.findById(custId);
			if (!customer.isPresent()) {
				throw new RecordNotFoundException("there is no customer with given id");
			}
			return customer;
		} catch (DataAccessException e) {
			throw new RecordNotFoundException("Error in fetching customer", e);
		}

	}

	@Override
	public Customer updateProfile(Customer customer) throws InvalidDataException {

		try {
			List<Customer> customers = customerRepository.findAll();

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

			return customer;
		} catch (DataAccessException e) {
			throw new InvalidDataException("Error in updating customer", e);
		}

	}

}
