package com.mindtree.fudo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.fudo.dto.CustomerDTO;
import com.mindtree.fudo.entity.Customer;
import com.mindtree.fudo.exceptions.CustomerException;
import com.mindtree.fudo.exceptions.MyApplicationException;
import com.mindtree.fudo.service.CustomerService;

@CrossOrigin
@RestController
@RequestMapping("/fudo/customerSignUp")
public class CustomerSignUpController 
{
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	private CustomerService customerservice;
	
//	method to send an email to validate email
	@GetMapping("/email")
	public ResponseEntity<?> email(@RequestParam("email") String email, @RequestParam("otp") int otp) 
	{
		SimpleMailMessage msg= new SimpleMailMessage();
		msg.setTo(email);
		
		
		msg.setSubject("OTP for Fudo Your Food Buddy");
		
		String o="Your OTP is :"+Integer.toString(otp)+"\n Enter this OTP to validate Your email!!";
		
		msg.setText(o);
		
		javaMailSender.send(msg);
		Map<String,Object> result= new HashMap<String,Object>();
		result.put("status", HttpStatus.OK);
		result.put("success", true);
		result.put("message","email sent to the user");
		System.out.println("done");
		return ResponseEntity.status(HttpStatus.OK).header("message", String.valueOf(HttpStatus.OK) + "_done")
					.body(result);
	}
	
//	method to register the customer
	@PostMapping("")
	public ResponseEntity<?> addCustomer(@Valid @RequestBody CustomerDTO customerdto)
	{
		Customer customer= new Customer();
		customer.setFirstName(customerdto.getFirstName());
		customer.setLastName(customerdto.getLastName());
		customer.setCustomerEmail(customerdto.getCustomerEmail().toLowerCase());
		String number=Long.toString(customerdto.getCustomerPhone());
		
		customer.setCustomerPhone(customerdto.getCustomerPhone());
		
		customer.setPassword(customerdto.getPassword());
		customer.setReferralCode(customerdto.getReferralCode());
		customer.setPoints(customerdto.getPoints());
		try 
		{
			Map<String,Object> result= new HashMap<String,Object>();
			if(number.length()!=10)
			{
//				System.out.println(number.length()+"exception");
					throw new MyApplicationException();
			}
			if(number.charAt(0)== '6'||number.charAt(0)== '7'||number.charAt(0)== '8'||number.charAt(0)== '9')
			{
				Customer custentity=customerservice.setSignup(customer);
				result.put("status", HttpStatus.OK);
				result.put("success", true);
				result.put("message","customer sign up succesfull");
				result.put("registeredobjectId",custentity.getCustomerId() );
				return ResponseEntity.status(HttpStatus.OK).header("message", String.valueOf(HttpStatus.OK) + "_done")
						.body(result);
				
			}
			else
			{
//				System.out.println("exception"+ number.charAt(0));
				throw new MyApplicationException();
			}
			
			
		} 
		catch (MyApplicationException e)
		{
			Map<String,Object> result= new HashMap<String,Object>();
			result.put("status", HttpStatus.BAD_REQUEST);
			result.put("success", false);
			result.put("message","Could not Register the user please check the details and try again!");
			return ResponseEntity.status(HttpStatus.OK).header("message", String.valueOf(HttpStatus.OK) + "_done")
					.body(result);
		}
		catch(Exception e)
		{
			Map<String,Object> result= new HashMap<String,Object>();
			result.put("status", HttpStatus.BAD_REQUEST);
			result.put("success", false);
			result.put("message","Could not Register the user please check the details and try again!");
			return ResponseEntity.status(HttpStatus.OK).header("message", String.valueOf(HttpStatus.OK) + "_done")
					.body(result);
		}
	}
	
//	method to get all the values from the database
	@GetMapping("/all")
	public List<Customer> getall()
	{
		return customerservice.getall();
	}

}
