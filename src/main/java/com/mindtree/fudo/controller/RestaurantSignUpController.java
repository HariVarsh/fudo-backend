package com.mindtree.fudo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.mindtree.fudo.dto.RestaurantDTO;
import com.mindtree.fudo.entity.City;
import com.mindtree.fudo.entity.Restaurant;
import com.mindtree.fudo.exceptions.MyApplicationException;
import com.mindtree.fudo.service.RestaurantSignup;

@CrossOrigin
@RestController
@RequestMapping("/fudo/restaurantsignup")
public class RestaurantSignUpController {

	@Autowired
	private RestaurantSignup resService;

	@Autowired
	private JavaMailSender mailsender;

	@GetMapping("/emailotp")
	public ResponseEntity<?> sendEmail(@RequestParam("email") String email, @RequestParam("otp") int otp) {
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo(email);

		msg.setSubject("OTP");

		String otpNumber = Integer.toString(otp);

		msg.setText(otpNumber);

		mailsender.send(msg);

		Map<String, Object> result = new HashMap<String, Object>();
		result.put("status", HttpStatus.OK);
		result.put("success", true);
		result.put("message", "email sent to the user");
		return ResponseEntity.status(HttpStatus.OK).header("message", String.valueOf(HttpStatus.OK) + "_done")
				.body(result);

	}

	@GetMapping("/restaurantlist")
	public List<Restaurant> getList() {
		return resService.getAll();
	}

	@PostMapping("")
	public ResponseEntity<?> addRestaurant(@Valid @RequestBody RestaurantDTO restaurantobjDTO) {

		HashMap<String, Object> result = new HashMap<>();

		Restaurant resObj = new Restaurant();
		City city = new City();

		resObj.setRestaurantName(restaurantobjDTO.getRestaurantName());
		resObj.setOwnerName(restaurantobjDTO.getOwnerName());
		resObj.setOwnerEmail(restaurantobjDTO.getOwnerEmail());
		resObj.setRestaurantPassword(restaurantobjDTO.getRestaurantPassword());
		resObj.setOwnerPhone(restaurantobjDTO.getOwnerPhone());
		resObj.setRestaurantAddress(restaurantobjDTO.getRestaurantAddress());

		city.setCityName(restaurantobjDTO.getRestaurantCity());
		resObj.setCity(city);

		resObj.setRestaurantCity(restaurantobjDTO.getRestaurantCity());
		resObj.setRestaurantAddress(restaurantobjDTO.getRestaurantName());
		
		resObj.setRestaurantStatus("open");

		String num = Long.toString(resObj.getOwnerPhone());
		try {
			if ((num.length() == 10)
					&& (num.charAt(0) == '6' || num.charAt(0) == '7' || num.charAt(0) == '8' || num.charAt(0) == '9')) {
				resService.addRestaurant(resObj);
				result.put("status", HttpStatus.OK);
				result.put("success", true);
				result.put("body", "Sign Up Successful");
				return ResponseEntity.status(HttpStatus.OK).header("message", String.valueOf(HttpStatus.OK) + "_done")
						.body(result);
			} else {
				throw new MyApplicationException();
			}

		} catch (MyApplicationException e) {
			result.put("status", HttpStatus.BAD_REQUEST);
			result.put("success", false);
			result.put("message", "Could not Register the Restaurant");
			return ResponseEntity.status(HttpStatus.OK).header("message", String.valueOf(HttpStatus.OK) + "_done")
					.body(result);
		}
	}

	/*
	 * Under Construction
	 * 
	 * 
	 * @RequestMapping("/fileupload") public ResponseEntity<?>
	 * fileUpload(@RequestParam("imageAsString") String imageAsString) {
	 * HashMap<String, Object> result = new HashMap<>(); try {
	 * resService.setImage(imageAsString);
	 * 
	 * result.put("status", HttpStatus.OK); result.put("success",true);
	 * result.put("body", "Sign Up Successful"); return
	 * ResponseEntity.status(HttpStatus.OK).header("message",
	 * String.valueOf(HttpStatus.OK) + "_done") .body(imageAsString); } catch
	 * (MyApplicationException e) { result.put("status", HttpStatus.BAD_REQUEST);
	 * result.put("success", false); result.put("message",
	 * "Could not add photo to Restaurant"); return
	 * ResponseEntity.status(HttpStatus.OK).header("message",
	 * String.valueOf(HttpStatus.OK) + "_done") .body(result); }
	 * 
	 * // System.out.println();
	 * 
	 * // System.out.println("Done");
	 * 
	 * // byte[] encoded = Base64.encodeBase64(imageAsString.getBytes()); //
	 * System.out.println(imageAsString); // File newFile = new File(); }
	 */
}
