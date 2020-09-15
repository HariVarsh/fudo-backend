package com.mindtree.fudo.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.fudo.dto.RestaurantDTO;
import com.mindtree.fudo.entity.Restaurant;
import com.mindtree.fudo.exceptions.MyApplicationException;
import com.mindtree.fudo.service.RestaurantService;

@CrossOrigin
@RestController
@RequestMapping("/fudo/restaurant")
public class DisplayRestaurantController {
	@Autowired
	RestaurantService restaurantService;

	/**
	 * 
	 * @param cityId
	 * @return response as list of restaurant on the basis of city id
	 */
	@GetMapping("/{cityId}")
	public ResponseEntity<?> getRestaurants(@PathVariable int cityId) {
		try {
			Map<String, Object> result = new HashMap<String, Object>();
			/**
			 * called service method to get restaurant from database
			 */
			Set<Restaurant> restaurant = restaurantService.getRestaurant(cityId);
			Set<RestaurantDTO> restaurantDto = new HashSet<RestaurantDTO>();
			/**
			 * Adding fetched list of restaurants to dto
			 */
			for (Restaurant rest : restaurant) {
				RestaurantDTO dtoObject = new RestaurantDTO();
				dtoObject.setRestaurantId(rest.getRestaurantId());
				dtoObject.setAverageRating(rest.getAverageRating());
				dtoObject.setCity(rest.getCity());
				dtoObject.setImageUrl(rest.getImageUrl());
				dtoObject.setRestaurantName(rest.getRestaurantName());
				dtoObject.setRestaurantStatus(rest.getRestaurantStatus());
				restaurantDto.add(dtoObject);
			}
			/**
			 * adding status success body to map
			 */
			result.put("status", HttpStatus.OK);
			result.put("success", true);
			result.put("body", restaurantDto);
			/**
			 * returning response as status, header,body
			 */
			return ResponseEntity.status(HttpStatus.OK).header("message", String.valueOf(HttpStatus.OK) + "_done")
					.body(restaurantDto);
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
