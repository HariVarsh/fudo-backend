package com.mindtree.fudo.service;

import java.util.List;
import com.mindtree.fudo.entity.Restaurant;
import com.mindtree.fudo.exceptions.MyApplicationException;
import com.mindtree.fudo.exceptions.NotValidEmail;
import com.mindtree.fudo.exceptions.NotaValidPassword;
import com.mindtree.fudo.exceptions.RecordNotFoundException;

public interface RestaurantLoginService {

	/**
	 * Method to update password for user already existing
	 * @param email
	 * @param password
	 * @throws RecordNotFoundException
	 * @throws NotaValidPassword 
	 */
	public String UpdateRestaurantpassword(String email, String password) throws RecordNotFoundException, NotaValidPassword; 

	/**
	 * send OTP to user for validating email
	 * @param otp
	 * @param email
	 * @throws NotValidEmail
	 * @throws RecordNotFoundException 
	 */
	public String sendOTP(String otp, String email) throws NotValidEmail, RecordNotFoundException;

	/**
	 * Get list of user
	 * @return list of restaurant 
	 * @throws Exception 
	 */
	public List<Restaurant> displayAll() throws MyApplicationException;

}
