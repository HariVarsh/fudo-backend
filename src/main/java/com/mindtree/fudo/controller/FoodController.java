package com.mindtree.fudo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.fudo.dto.FoodDTO;
import com.mindtree.fudo.entity.Food;
import com.mindtree.fudo.exceptions.MyApplicationException;
import com.mindtree.fudo.exceptions.RecordNotFoundException;
import com.mindtree.fudo.service.FoodService;

@RestController
@CrossOrigin
@RequestMapping("/fudo/food")
public class FoodController {
	@Autowired
	FoodService foodservice;

	/**
	 * 
	 * @param restaurantId inserted to get food for that particular restaurant
	 * @return retuning food forgiven restaurant id
	 */
	@GetMapping("/{restaurantId}")
	public ResponseEntity<?> getFoodByRestaurantId(@Valid @PathVariable int restaurantId) {
		try {
			Map<String, Object> result = new HashMap<String, Object>();
			List<Food> foodForRestaurant = foodservice.getFoodById(restaurantId);
			List<FoodDTO> foodDto = new ArrayList<FoodDTO>();
			// setting food entity attributes to food dto
			for (Food food : foodForRestaurant) {
				FoodDTO foodDtoObj = new FoodDTO();
				foodDtoObj.setCuisine(food.getCuisine());
				foodDtoObj.setFoodId(food.getFoodId());
				foodDtoObj.setFoodName(food.getFoodName());
				foodDtoObj.setFoodStatus(food.getFoodStatus());
				foodDtoObj.setPrice(food.getPrice());
				foodDtoObj.setRestaurant(food.getRestaurant());
				foodDtoObj.setFoodType(food.getFoodType());
				foodDto.add(foodDtoObj);
			}
			result.put("status", HttpStatus.OK);
			result.put("success", true);
			result.put("body", foodDto);

			// returning response entity for food
			return ResponseEntity.status(HttpStatus.OK).header("message", String.valueOf(HttpStatus.OK) + "_done")
					.body(foodDto);

		}

		// catching exceptions down there and passing response entity for those
		// conditions...

		catch (MyApplicationException e) {
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", HttpStatus.NOT_FOUND);
			result.put("success", false);
			result.put("exception", e.getMessage());
			return ResponseEntity.status(HttpStatus.OK).header("status", String.valueOf(HttpStatus.NOT_FOUND))
					.body(result);

		}

		catch (Exception e) {
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
	
	
	@GetMapping("/get/{restaurantId}")
	public ResponseEntity<?> getFoodByRestaurantIdAll(@Valid @PathVariable int restaurantId) {
		try {
			Map<String, Object> result = new HashMap<String, Object>();
			List<Food> foodForRestaurant = foodservice.getFoodByIdAll(restaurantId);
			List<FoodDTO> foodDto = new ArrayList<FoodDTO>();
			// setting food entity attributes to food dto
			for (Food food : foodForRestaurant) {
				FoodDTO foodDtoObj = new FoodDTO();
				foodDtoObj.setCuisine(food.getCuisine());
				foodDtoObj.setFoodId(food.getFoodId());
				foodDtoObj.setFoodName(food.getFoodName());
				foodDtoObj.setFoodStatus(food.getFoodStatus());
				foodDtoObj.setPrice(food.getPrice());
				foodDtoObj.setRestaurant(food.getRestaurant());
				foodDtoObj.setFoodType(food.getFoodType());
				foodDto.add(foodDtoObj);
			}
			result.put("status", HttpStatus.OK);
			result.put("success", true);
			result.put("body", foodDto);

			// returning response entity for food
			return ResponseEntity.status(HttpStatus.OK).header("message", String.valueOf(HttpStatus.OK) + "_done")
					.body(foodDto);

		}

		// catching exceptions down there and passing response entity for those
		// conditions...

		catch (MyApplicationException e) {
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", HttpStatus.NOT_FOUND);
			result.put("success", false);
			result.put("exception", e.getMessage());
			return ResponseEntity.status(HttpStatus.OK).header("status", String.valueOf(HttpStatus.NOT_FOUND))
					.body(result);

		}

		catch (Exception e) {
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

	@PostMapping("/insert")
	public ResponseEntity<?> insertFoodIntoRestaurant(@Valid @RequestBody FoodDTO foodDto) throws RecordNotFoundException {
		try {
		Map<String, Object> result = new HashMap<String, Object>();

		Food food = new Food();
		food.setFoodName(foodDto.getFoodName());
		food.setFoodType(foodDto.getFoodType());
		food.setPrice(foodDto.getPrice());
		food.setRestaurant(foodDto.getRestaurant());
		food.setCuisine(foodDto.getCuisine());
		food.setFoodStatus(foodDto.getFoodStatus());

		result.put("status", HttpStatus.OK);
		result.put("success", true);
		result.put("body", foodservice.insertFood(food));

		return ResponseEntity.status(HttpStatus.OK).header("status", String.valueOf(HttpStatus.OK)).body(result);
		}
		
		catch(MyApplicationException e) {
			
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", HttpStatus.NOT_FOUND);
			result.put("success", false);
			result.put("exception", e.getMessage());
			return ResponseEntity.status(HttpStatus.OK).header("status", String.valueOf(HttpStatus.NOT_FOUND))
					.body(result);
		}
		catch (Exception e) {
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
			result.put("success", false);
			result.put("message", "Something Went wrong in inserting food");
			result.put("exception", e.getMessage());
			result.put("cause", e.getCause());
			return ResponseEntity.status(HttpStatus.OK)
					.header("status", String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR)).body(result);
		}
	}

	@PutMapping("/status")
	public ResponseEntity<?> updateStatus(@Valid @RequestBody FoodDTO foodDto) {
		try {
			Map<String, Object> result = new HashMap<String, Object>();

			Food food = new Food();
			food.setFoodStatus(foodDto.getFoodStatus());
			food.setFoodId(foodDto.getFoodId());
			result.put("status", HttpStatus.OK);
			result.put("success", true);
			result.put("body", foodservice.updateStatus(food));

			return ResponseEntity.status(HttpStatus.OK).header("status", String.valueOf(HttpStatus.OK)).body(result);
		} catch (MyApplicationException e) {

			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", HttpStatus.NOT_FOUND);
			result.put("success", false);
			result.put("exception", e.getMessage());
			return ResponseEntity.status(HttpStatus.OK).header("status", String.valueOf(HttpStatus.NOT_FOUND))
					.body(result);

		}
		catch (Exception e) {
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
			result.put("success", false);
			result.put("message", "Something Went wrong in updating status");
			result.put("exception", e.getMessage());
			result.put("cause", e.getCause());
			return ResponseEntity.status(HttpStatus.OK)
					.header("status", String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR)).body(result);
		}
	}

	@PutMapping("/update")
	public ResponseEntity<?> updateFood(@Valid @RequestBody FoodDTO foodDto) {
		try {
			Map<String, Object> result = new HashMap<String, Object>();
			Food food = new Food();
			
			food.setFoodId(foodDto.getFoodId());
			food.setFoodName(foodDto.getFoodName());
			food.setFoodType(foodDto.getFoodType());
			food.setPrice(foodDto.getPrice());
			result.put("status", HttpStatus.OK);
			result.put("success", true);
			result.put("body", foodservice.updateFood(food));
			return ResponseEntity.status(HttpStatus.OK).header("status", String.valueOf(HttpStatus.OK)).body(result);
		} catch (MyApplicationException e) {
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", HttpStatus.NOT_FOUND);
			result.put("success", false);
			result.put("exception", e.getMessage());
			return ResponseEntity.status(HttpStatus.OK).header("status", String.valueOf(HttpStatus.NOT_FOUND))
					.body(result);
		}
		catch (Exception e) {
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
			result.put("success", false);
			result.put("message", "Something Went wrong in updating food");
			result.put("exception", e.getMessage());
			result.put("cause", e.getCause());
			return ResponseEntity.status(HttpStatus.OK)
					.header("status", String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR)).body(result);
		}
	}

	@DeleteMapping("/delete")
	public ResponseEntity<?> deleteFood(@Valid @RequestParam int foodId) {
		try {
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", HttpStatus.OK);
			result.put("success", true);
			result.put("body", foodservice.deleteFood(foodId));
			return ResponseEntity.status(HttpStatus.OK).header("status", String.valueOf(HttpStatus.OK)).body(result);
		} catch (MyApplicationException e) {
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", HttpStatus.NOT_FOUND);
			result.put("success", false);
			result.put("exception", e.getMessage());
			return ResponseEntity.status(HttpStatus.OK).header("status", String.valueOf(HttpStatus.NOT_FOUND))
					.body(result);

		}
		catch (Exception e) {
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
			result.put("success", false);
			result.put("message", "Something Went wrong in deleting food");
			result.put("exception", e.getMessage());
			result.put("cause", e.getCause());
			return ResponseEntity.status(HttpStatus.OK)
					.header("status", String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR)).body(result);
		}
	}
}