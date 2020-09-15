package com.mindtree.fudo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.fudo.dto.MyOrderDTO;
import com.mindtree.fudo.entity.MyOrder;
import com.mindtree.fudo.exceptions.MyApplicationException;
import com.mindtree.fudo.service.RestaurantsOrdersService;


@CrossOrigin
@RestController
@RequestMapping("/fudo/RestaurantsOrders")
public class RestaurantsOrderController {
	@Autowired
	RestaurantsOrdersService rService;
	
	@GetMapping("/{restaurantId}")
	public ResponseEntity<?> getAllOrdersInDb(@PathVariable int restaurantId)
	{
		
		try {
			Map<String, Object> result = new HashMap<String, Object>();
			List<MyOrderDTO> orderDto=new ArrayList<MyOrderDTO>();
			List<MyOrder> orders=new ArrayList<MyOrder>();
			orders=rService.getOrders(restaurantId);
			for(MyOrder o:orders)
			{
				MyOrderDTO dto=new MyOrderDTO();
				dto.setOrderId(o.getOrderId());;
				dto.setOrderDate(o.getOrderDate());
				dto.setRating(o.getRating());
				dto.setTotalPrice(o.getTotalPrice());
				dto.setAddress(o.getAddress());
				dto.setCustomer(o.getCustomer());
				dto.setRestaurant(o.getRestaurant());
				dto.setFoodList(o.getFoodList());
				orderDto.add(dto);
			}
			
			result.put("status", HttpStatus.OK);
			result.put("success", true);
			result.put("body", orderDto);
			return ResponseEntity.status(HttpStatus.OK).header("message", String.valueOf(HttpStatus.OK) + "_done").body(orderDto);
		} catch (MyApplicationException e) {
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", HttpStatus.NOT_FOUND);
			result.put("success", false);
			result.put("exception", e.getMessage());
			result.put("cause", e.getCause());
			return ResponseEntity.status(HttpStatus.OK).header("status", String.valueOf(HttpStatus.NOT_FOUND)).body(result);
		}
		catch (Exception e) {
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", HttpStatus.NOT_FOUND);
			result.put("success", false);
			result.put("exception", e.getMessage());
			result.put("cause", e.getCause());
			return ResponseEntity.status(HttpStatus.OK).header("status", String.valueOf(HttpStatus.NOT_FOUND)).body(result);
		}
		
	}

}
