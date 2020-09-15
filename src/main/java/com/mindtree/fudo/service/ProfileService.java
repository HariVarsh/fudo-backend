package com.mindtree.fudo.service;
import java.util.Optional;
import com.mindtree.fudo.entity.Customer;
import com.mindtree.fudo.exceptions.InvalidDataException;
import com.mindtree.fudo.exceptions.RecordNotFoundException;

public interface ProfileService {
	public Optional<Customer> getProfile(int custId) throws RecordNotFoundException;
	public Customer updateProfile(Customer customer)  throws InvalidDataException;
}
