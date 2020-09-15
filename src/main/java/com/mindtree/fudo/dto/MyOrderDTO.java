package com.mindtree.fudo.dto;

import java.sql.Date;
import java.util.List;

import com.mindtree.fudo.entity.Customer;
import com.mindtree.fudo.entity.Food;
import com.mindtree.fudo.entity.Restaurant;

public class MyOrderDTO {
	
	private int orderId;
	private Date orderDate;
	private int rating;
	private double totalPrice;
	private String address;
	Customer customer;
	Restaurant restaurant;
	List<Food> foodList;

	public MyOrderDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MyOrderDTO(int orderId, Date orderDate, int rating, double totalPrice, String address, Customer customer,
			Restaurant restaurant, List<Food> foodList) {
		super();
		this.orderId = orderId;
		this.orderDate = orderDate;
		this.rating = rating;
		this.totalPrice = totalPrice;
		this.address = address;
		this.customer = customer;
		this.restaurant = restaurant;
		this.foodList = foodList;
	}
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public List<Food> getFoodList() {
		return foodList;
	}

	public void setFoodList(List<Food> foodList) {
		this.foodList = foodList;
	}

	@Override
	public String toString() {
		return "MyOrder [orderId=" + orderId + ", orderDate=" + orderDate + ", rating=" + rating + ", totalPrice="
				+ totalPrice + ", customer=" + customer + ", restaurant=" + restaurant + ", foodList=" + foodList + "]";
	}
	
}
