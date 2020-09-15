package com.mindtree.fudo.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.fudo.dto.CustomerDTO;
import com.mindtree.fudo.entity.Customer;
import com.mindtree.fudo.exceptions.MyApplicationException;
import com.mindtree.fudo.service.ProfileService;

@RestController
@CrossOrigin
@RequestMapping("/fudo/profile")
public class ProfileController {

	@Autowired
	ProfileService profileService;

	@GetMapping("/{custId}")
	/**
	 * 
	 * @param custId
	 * @return customer details on the basis of customer id
	 */
	public ResponseEntity<?> getProfile(@PathVariable int custId) {

		try {

			Optional<Customer> customer = profileService.getProfile(custId);
			return ResponseEntity.status(HttpStatus.OK).header("message", String.valueOf(HttpStatus.OK) + "_done")
					.body(customer);
		}

		/**
		 * catching the exceptions down there
		 */
		catch (MyApplicationException e) {
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", HttpStatus.NOT_FOUND);
			result.put("success", false);
			result.put("exception", e.getMessage());
			result.put("cause", e.getCause());
			return ResponseEntity.status(HttpStatus.OK).header("status", String.valueOf(HttpStatus.NOT_FOUND))
					.body(result);

		} catch (Exception e) {
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
			result.put("success", false);
			result.put("message", "Something Went wrong in getting food");
			result.put("exception", e.getMessage());
			result.put("cause", e.getCause());
			return ResponseEntity.status(HttpStatus.OK)
					.header("status", String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR)).body(result);
		}
	}
	
	
	@PutMapping("/update")
	/**
	 * 
	 * @param custId
	 * @return updated list of profile
	 */
	public ResponseEntity<?> updateProfile(@Valid @RequestBody CustomerDTO customerDTO) {

		try {
			System.out.println(customerDTO);
            Customer customer = new Customer();
            customer.setCustomerEmail(customerDTO.getCustomerEmail());
            customer.setCustomerId(customerDTO.getCustomerId());
            customer.setCustomerPhone(customerDTO.getCustomerPhone());
            customer.setFirstName(customerDTO.getFirstName());
            customer.setLastName(customerDTO.getLastName());
            customer.setPassword(customerDTO.getPassword());
            customer.setPoints(customerDTO.getPoints());
            customer.setReferralCode(customerDTO.getReferralCode());
			profileService.updateProfile(customer);
			return ResponseEntity.status(HttpStatus.OK).header("message", String.valueOf(HttpStatus.OK) + "_done")
					.body(customer);
		}

		/**
		 * catching the exceptions down there
		 */
		catch (MyApplicationException e) {
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", HttpStatus.NOT_FOUND);
			result.put("success", false);
			result.put("exception", e.getMessage());
			result.put("cause", e.getCause());
			return ResponseEntity.status(HttpStatus.OK).header("status", String.valueOf(HttpStatus.NOT_FOUND))
					.body(result);

		} catch (Exception e) {
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
			result.put("success", false);
			result.put("message", "Something Went wrong in getting food");
			result.put("exception", e.getMessage());
			result.put("cause", e.getCause());
			return ResponseEntity.status(HttpStatus.OK)
					.header("status", String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR)).body(result);
		}
	}
}

