package com.mindtree.fudo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int cartId;

	private String customerEmail;
private String foodName;
	private int foodId;
	private int count;
	private int restaurantId;
	private double price;
	public double getPrice() {
		return price;
	}
	public Cart(int cartId, String customerEmail, String foodName, int foodId, int count, int restaurantId,
			double price) {
		super();
		this.cartId = cartId;
		this.customerEmail = customerEmail;
		this.foodName = foodName;
		this.foodId = foodId;
		this.count = count;
		this.restaurantId = restaurantId;
		this.price = price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getFoodName() {
		return foodName;
	}
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}
	public int getCartId() {
		return cartId;
	}
	public void setCartId(int cartId) {
		this.cartId = cartId;
	}
	public String getCustomerEmail() {
		return customerEmail;
	}
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}
	@Override
	public String toString() {
		return "Cart [cartId=" + cartId + ", customerEmail=" + customerEmail + ", foodId=" + foodId + ", count=" + count
				+ ", restaurantId=" + restaurantId + ", price=" + price + "]";
	}
	public int getFoodId() {
		return foodId;
	}
	public void setFoodId(int foodId) {
		this.foodId = foodId;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getRestaurantId() {
		return restaurantId;
	}
	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}
	public Cart() {
		super();
	}
	
	

}
