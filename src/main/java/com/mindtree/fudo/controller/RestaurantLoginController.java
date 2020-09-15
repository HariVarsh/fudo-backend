package com.mindtree.fudo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.mindtree.fudo.dto.UpdatePasswordDto;
import com.mindtree.fudo.entity.Restaurant;
import com.mindtree.fudo.exceptions.MyApplicationException;
import com.mindtree.fudo.service.RestaurantLoginService;

@RestController
@CrossOrigin
@RequestMapping("fudo/restlogin")
public class RestaurantLoginController {
	
	@Autowired
	private RestaurantLoginService restaurant_login_service;
	
	/**
	 * Method calling the method of service class to fetch all the details of customer
	 * @return
	 */
	@GetMapping("/userdata")
	public ResponseEntity<?> getall() {
		
		
		try {

			Map<String, Object> result = new HashMap<String, Object>();
			List<Restaurant> restaurantlist=restaurant_login_service.displayAll();
			result.put("status", HttpStatus.OK);
			result.put("success", true);
			result.put("message",restaurantlist);
			return ResponseEntity.status(HttpStatus.OK).header("message", String.valueOf(HttpStatus.OK) + "_done")
					.body(result);
		} catch (MyApplicationException e) {
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", HttpStatus.BAD_REQUEST);
			result.put("success", false);
			result.put("message", e.getMessage());
			return ResponseEntity.status(HttpStatus.OK).header("message", String.valueOf(HttpStatus.OK) + "_done")
					.body(result);
		} 
	}
	
	
	/**
	 * Method calling the method of service class to send OTP to valid  mail
	 * @param otp
	 * @param email
	 * @return
	 */
	@GetMapping("/otp")
	public ResponseEntity<?> sendotp(@RequestParam("email") String email, @RequestParam("otp") String otp) {
		try {

			Map<String, Object> result = new HashMap<String, Object>();
			String message=restaurant_login_service.sendOTP(otp, email);
			result.put("status", HttpStatus.OK);
			result.put("success", true);
			result.put("message", message);
			return ResponseEntity.status(HttpStatus.OK).header("message", String.valueOf(HttpStatus.OK) + "_done")
					.body(result);

		} catch (MyApplicationException e) {
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", HttpStatus.BAD_REQUEST);
			result.put("success", false);
			result.put("message", e.getMessage());
			return ResponseEntity.status(HttpStatus.OK).header("message", String.valueOf(HttpStatus.OK) + "_done")
					.body(result);
		} 
	}
	
	/**
	 * calling the method of service class to update password of existing user
	 * @param email
	 * @param password
	 * @return
	 */
	@PostMapping("/newpassword")
	public ResponseEntity<?> updatepassword(@RequestBody UpdatePasswordDto updatePasswordDto) {
		try {
			System.out.println("inside update password controller");
			Map<String, Object> result = new HashMap<String, Object>();
			String message= restaurant_login_service.UpdateRestaurantpassword(updatePasswordDto.getEmail(),updatePasswordDto.getPassword());
			result.put("status", HttpStatus.OK);
			result.put("success", true);
			result.put("message", message);
			return ResponseEntity.status(HttpStatus.OK).header("message", String.valueOf(HttpStatus.OK) + "_done")
					.body(result);

		} catch (MyApplicationException e) {
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", HttpStatus.BAD_REQUEST);
			result.put("success", false);
			result.put("message", e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).header("message", String.valueOf(HttpStatus.BAD_REQUEST) + "_done")
					.body(result);
		} 
	}
	
}
