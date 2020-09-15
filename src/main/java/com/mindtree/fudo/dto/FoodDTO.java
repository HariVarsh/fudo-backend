package com.mindtree.fudo.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.mindtree.fudo.entity.Cuisine;
import com.mindtree.fudo.entity.MyOrder;
import com.mindtree.fudo.entity.Restaurant;

public class FoodDTO {

	
//	@NotBlank(message="Food Id is required")
	private int foodId;

//	@NotBlank(message="Food is required")
//	@NotNull(message="Food cannot be null")
//	@Pattern(message="No invalid charecters Allowed in food name",regexp="^[a-zA-Z]+$")
	private String foodName;
	

	private double price;
	
	
//	@NotBlank(message="Status is required")
//	@NotNull(message="Status cannot be null")
	
	private String foodStatus;
	private Cuisine cuisine;
	private Restaurant restaurant;
	private List<MyOrder> orderList;
//	@NotBlank(message="FoodType is required")
	
	private String foodType;

	public FoodDTO() {
		super();
	}

	public FoodDTO(int foodId, String foodName, double price, String foodStatus, Cuisine cuisine,
			Restaurant restaurant) {
		super();
		this.foodId = foodId;
		this.foodName = foodName;
		this.price = price;
		this.foodStatus = foodStatus;
		this.cuisine = cuisine;
		this.restaurant = restaurant;

	}

	public int getFoodId() {
		return foodId;
	}

	public void setFoodId(int foodId) {
		this.foodId = foodId;
	}

	public String getFoodName() {
		return foodName;
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * @return the foodStatus
	 */
	public String getFoodStatus() {
		return foodStatus;
	}

	public void setFoodStatus(String foodStatus) {
		this.foodStatus = foodStatus;
	}

	public Cuisine getCuisine() {
		return cuisine;
	}

	public void setCuisine(Cuisine cuisine) {
		this.cuisine = cuisine;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	/**
	 * @return the foodType
	 */
	public String getFoodType() {
		return foodType;
	}

	/**
	 * @param foodType the foodType to set
	 */
	public void setFoodType(String foodType) {
		this.foodType = foodType;
	}

	@Override
	public String toString() {
		return "Food [foodId=" + foodId + ", foodName=" + foodName + ", price=" + price + ", foodStatus=" + foodStatus
				+ ", cuisine=" + cuisine + ", restaurant=" + restaurant + "]";
	}
}
