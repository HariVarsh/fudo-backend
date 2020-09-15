package com.mindtree.fudo.entity;
import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class MyOrder {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	@Column(name="orderId",nullable=false,unique=true)
	private int orderId;
	
	//
	@Column(name="orderDate",nullable=false)
	private Date orderDate;
	
	@Column(name="rating",nullable=false)
	private int rating;
	
	@Column(name="totalPrice",nullable=false)
	private double totalPrice;
	
	@Column(name="address",nullable=false)
	private String address;
	
	@ManyToOne
	private Customer customer;
	
	@ManyToOne
	private Restaurant restaurant;
	
	@ManyToMany
	private List<Food> foodList;
	
	
	public MyOrder() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public MyOrder(int orderId, Date orderDate, int rating, double totalPrice, Customer customer, Restaurant restaurant,
			List<Food> foodList, String address) {
		super();
		this.orderId = orderId;
		this.orderDate = orderDate;
		this.rating = rating;
		this.totalPrice = totalPrice;
		this.customer = customer;
		this.restaurant = restaurant;
		this.foodList = foodList;
		this.address = address;
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
				+ totalPrice + ",address="+address+"]";
	}
	
}
