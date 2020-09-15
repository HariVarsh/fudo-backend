package com.mindtree.fudo.service.impl;

import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import com.mindtree.fudo.entity.Customer;
import com.mindtree.fudo.exceptions.NotValidEmail;
import com.mindtree.fudo.exceptions.NotaValidPassword;
import com.mindtree.fudo.exceptions.RecordNotFoundException;
import com.mindtree.fudo.repository.CustomerRepository;
import com.mindtree.fudo.service.CustomerLoginService;

@Service
public class CustomerLoginServiceImpl implements CustomerLoginService {

	@Autowired
	private CustomerRepository customerrepository;

	@Autowired
	private JavaMailSender jms;

	/**
	 * Method to update password for user already existing
	 * 
	 * @throws NotaValidPassword
	 */
	@Override
	public String UpdateCustomerpassword(String email, String password)
			throws RecordNotFoundException, NotaValidPassword {

		int flag = 0;
		boolean smallChar = false;
		boolean capChar = false;
		boolean digit = false;
		boolean passowrdlength = false;

		if (password.length() >= 6 || password.length() <= 10) {
			passowrdlength = true;
		}

		for (int i = 0; i < password.length(); i++) {
			if (password.charAt(i) >= 'a' && password.charAt(i) <= 'z') {
				smallChar = true;
			} else if (password.charAt(i) >= 'A' && password.charAt(i) <= 'Z') {
				capChar = true;
			} else if (password.charAt(i) >= '0' && password.charAt(i) <= '9') {
				digit = true;
			}
		}

		if (smallChar && capChar && digit && passowrdlength) {
			List<Customer> list = customerrepository.findAll();
			for (Customer c : list) {
				if (c.getCustomerEmail().equalsIgnoreCase(email)) // comparing the email enter by user with the database
																	// email
				{
					c.setPassword(password);
					customerrepository.save(c);
					flag = 1;
					break;
				}
			}
		} else {
			throw new NotaValidPassword("enter valid password");
		}
		if (flag == 0) // if no email is matched from database then throwing an exception
		{
			throw new RecordNotFoundException("Enter registered email");
		} else {
			return "password updated successfully";
		}

	}

	/**
	 * Method to send OTP to valid mail
	 * @throws RecordNotFoundException
	 */
	@Override
	public String sendOTP(String otp, String email) throws NotValidEmail, RecordNotFoundException {

		SimpleMailMessage msg = new SimpleMailMessage();
		int flag = 0;
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
				+ "A-Z]{2,7}$";
		List<Customer> customerlist = customerrepository.findAll();
		Pattern pat = Pattern.compile(emailRegex);
		if (email != "" && (pat.matcher(email).matches())) // checking
		{
			List<Customer> filteredList = customerlist.stream()
					.filter(x -> x.getCustomerEmail().equalsIgnoreCase(email)).collect(Collectors.toList());
			Iterator<Customer> Iterator = filteredList.iterator();
			while (Iterator.hasNext()) {
				Iterator.next();
				flag = 1;
				msg.setTo(email);
				msg.setSubject("Your OTP for changing password");
				msg.setText("your otp is " + otp);
				jms.send(msg);
			}
			if (flag == 0)
				throw new RecordNotFoundException("Record was not found");
			else {
				return "otp send successfully";
			}
		} else {
			throw new NotValidEmail("Not valid email");
		}

	}

	/**
	 * Method get details of all the customer
	 */
	@Override
	public List<Customer> displayAll() {
		return customerrepository.findAll();
	}

}
