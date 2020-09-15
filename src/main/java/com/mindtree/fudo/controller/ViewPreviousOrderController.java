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
import com.mindtree.fudo.service.PreviousOrders;
@CrossOrigin
@RestController
@RequestMapping("/fudo/viewPreviousOrders")
public class ViewPreviousOrderController {
	
	@Autowired
	PreviousOrders previousOrderService;
		
//		Getting all the previous orders done by the customer
		
	@GetMapping("/{customerId}")
	public ResponseEntity<?> getAllOrders(@PathVariable int customerId){
		
		
		try {
			Map<String, Object> result = new HashMap<String, Object>();
			List<MyOrderDTO> orderDto=new ArrayList<MyOrderDTO>();
			List<MyOrder> orders=previousOrderService.getAllOrders(customerId);
		for(MyOrder o:orders)
		{
			MyOrderDTO dto=new MyOrderDTO();
			dto.setOrderId(o.getOrderId());;
			dto.setOrderDate(o.getOrderDate());
			dto.setRating(o.getRating());
			dto.setTotalPrice(o.getTotalPrice());
			dto.setAddress(o.getAddress());
			dto.setCustomer(o.getCustomer());
//			dto.setRestaurant(o.getRestaurant());
			dto.setFoodList(o.getFoodList());
			orderDto.add(dto);
		}
		result.put("status", HttpStatus.OK);
		result.put("success", true);
		result.put("body", orderDto);
		return ResponseEntity.status(HttpStatus.OK).header("message", String.valueOf(HttpStatus.OK) + "_done").body(orderDto);
		}
		catch(MyApplicationException e) {
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", HttpStatus.NOT_FOUND);
			result.put("success", false);
			result.put("exception", e.getMessage());
			result.put("cause", e.getCause());
			return ResponseEntity.status(HttpStatus.OK).header("status", String.valueOf(HttpStatus.NOT_FOUND))
					.body(result);
		}catch (Exception e) {
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
