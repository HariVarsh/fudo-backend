package com.mindtree.fudo.controller;

import java.util.HashMap;

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
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.fudo.entity.Cart;
import com.mindtree.fudo.entity.MyOrder;
import com.mindtree.fudo.exceptions.MyApplicationException;
import com.mindtree.fudo.service.OrderService;

@CrossOrigin
@RestController
@RequestMapping("/fudo/order")
public class MyOrderController {

	@Autowired
	OrderService orderService;

	/*
	 * Adding the items to the cart table
	 */

	@PostMapping("/addToCart")
	public ResponseEntity<?> addToCart(@RequestBody Cart cart) {
		HashMap<String, Object> result = new HashMap<>();
		try {
			orderService.addToCart(cart);
			result.put("status", HttpStatus.OK);
			result.put("body", "success");
			return ResponseEntity.status(HttpStatus.OK).header("status", String.valueOf(HttpStatus.OK)).body(result);
		} catch (MyApplicationException e) {
			result.put("exception", e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.header("status", String.valueOf(HttpStatus.BAD_REQUEST)).body(result);
		}
	}

	/*
	 * deleting a particular food ID present in the cart table of a particular user
	 * email
	 */
	@DeleteMapping("/deleteCart/{customerEmail}/{foodId}")
	public ResponseEntity<?> deleteFromCartById(@PathVariable("customerEmail") String customerEmail,
			@PathVariable("foodId") int foodId) {
		HashMap<String, Object> result = new HashMap<>();
		try {
			orderService.deleteFromCartById(customerEmail, foodId);
			result.put("status", HttpStatus.OK);
			result.put("body", "success");
			return ResponseEntity.status(HttpStatus.OK).header("status", String.valueOf(HttpStatus.OK)).body(result);
		} catch (MyApplicationException e) {
			result.put("exception", e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.header("status", String.valueOf(HttpStatus.BAD_REQUEST)).body(result);
		}

	}

	/*
	 * deleting the entire cart of that user email
	 */
	@DeleteMapping("/deleteCart/{customerEmail}")
	public ResponseEntity<?> deleteAll(@PathVariable("customerEmail") String customerEmail) {
		HashMap<String, Object> result = new HashMap<>();
		try {
			orderService.deleteAll(customerEmail);
			result.put("status", HttpStatus.OK);
			result.put("body", "success");
			return ResponseEntity.status(HttpStatus.OK).header("status", String.valueOf(HttpStatus.OK)).body(result);
		} catch (MyApplicationException e) {
			result.put("exception", e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.header("status", String.valueOf(HttpStatus.BAD_REQUEST)).body(result);
		}

	}

	/*
	 * display the items present in cart
	 */
	@GetMapping("/displayCart/{customerEmail}")
	public ResponseEntity<?> displayCart(@PathVariable("customerEmail") String customerEmail) {
		HashMap<String, Object> result = new HashMap<>();

		result.put("status", HttpStatus.OK);
		result.put("body", "success");
		result.put("output", orderService.displayCart(customerEmail));
		return ResponseEntity.status(HttpStatus.OK).header("status", String.valueOf(HttpStatus.OK)).body(result);

	}

	/*
	 * searching if the item is already present in cart table, if present the count
	 * alone is incremented else a new row is added to the cart table
	 */
	@GetMapping("/searchCart/{customerEmail}/{foodId}")
	public ResponseEntity<?> searchCart(@PathVariable("customerEmail") String customerEmail,
			@PathVariable("foodId") int foodId) {
		boolean valid = orderService.searchCart(customerEmail, foodId);
		HashMap<String, Object> result = new HashMap<>();
		result.put("status", HttpStatus.OK);
		result.put("body", valid);
		return ResponseEntity.status(HttpStatus.OK).header("status", String.valueOf(HttpStatus.OK)).body(result);
	}

	/*
	 * add all the order details to the db
	 */
	@PostMapping("/placeOrder")
	public ResponseEntity<?> placeOrder(@RequestBody MyOrder myOrders) {
		HashMap<String, Object> result = new HashMap<>();
		try {
			System.out.println(myOrders);
			orderService.placeOrder(myOrders);
			result.put("status", HttpStatus.OK);
			result.put("body", "success");
			return ResponseEntity.status(HttpStatus.OK).header("status", String.valueOf(HttpStatus.OK)).body(result);
		} catch (MyApplicationException e) {
			result.put("status", HttpStatus.BAD_REQUEST);
			result.put("exception", e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.header("status", String.valueOf(HttpStatus.BAD_REQUEST)).body(result);
		}
	}

	/*
	 * updating the coin count after payment
	 */
	@PutMapping("/updateCoins/{customerEmail}/{coin}")
	public ResponseEntity<?> updateCoins(@PathVariable("customerEmail") String customerEmail,
			@PathVariable("coin") int coin) {
		HashMap<String, Object> result = new HashMap<>();
		try {
			orderService.updateCoins(customerEmail, coin);
			result.put("status", HttpStatus.OK);
			result.put("body", "success");
			return ResponseEntity.status(HttpStatus.OK).header("status", String.valueOf(HttpStatus.OK)).body(result);
		} catch (MyApplicationException e) {
			result.put("status", HttpStatus.BAD_REQUEST);
			result.put("exception", e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.header("status", String.valueOf(HttpStatus.BAD_REQUEST)).body(result);
		}

	}

	/*
	 * fetching the coins that the customer has
	 */
	@GetMapping("/getCoins/{customerEmail}")
	public ResponseEntity<?> getCoins(@PathVariable("customerEmail") String customerEmail) {

		HashMap<String, Object> result = new HashMap<>();
		try {

			result.put("status", HttpStatus.OK);
			result.put("body", "success");
			result.put("output", orderService.getCoins(customerEmail));
			return ResponseEntity.status(HttpStatus.OK).header("status", String.valueOf(HttpStatus.OK)).body(result);
		} catch (MyApplicationException e) {
			result.put("status", HttpStatus.BAD_REQUEST);
			result.put("exception", e.getMessage());
			result.put("cause", e.getCause());//doubt
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.header("status", String.valueOf(HttpStatus.BAD_REQUEST)).body(result);
		}
	}

}
