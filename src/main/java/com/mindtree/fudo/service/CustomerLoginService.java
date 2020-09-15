package com.mindtree.fudo.service;

import java.util.List;

import com.mindtree.fudo.entity.Customer;
import com.mindtree.fudo.exceptions.NotValidEmail;
import com.mindtree.fudo.exceptions.NotaValidPassword;
import com.mindtree.fudo.exceptions.RecordNotFoundException;

public interface CustomerLoginService {

	/**
	 * Method to update password for user already existing
	 * @param email
	 * @param password
	 * @return 
	 * @throws RecordNotFoundException
	 * @throws NotaValidPassword 
	 */
	public String UpdateCustomerpassword(String email, String password) throws RecordNotFoundException, NotaValidPassword; 

	/**
	 * send OTP to user for validating email
	 * @param otp
	 * @param email
	 * @return 
	 * @throws NotValidEmail
	 * @throws RecordNotFoundException 
	 */
	public String sendOTP(String otp, String email) throws NotValidEmail, RecordNotFoundException;

	/**
	 * Get list of user
	 * @return
	 */
	public List<Customer> displayAll();

}
