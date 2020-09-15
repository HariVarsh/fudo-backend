package com.mindtree.fudo.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "food")
public class Food {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "foodId", nullable = false, unique = true)
	private int foodId;

	@Column(name = "foodName", nullable = false)
	private String foodName;

	@Column(name = "price", nullable = false)
	private double price;

	@Column(name = "foodStatus")
	private String foodStatus;

	@Column(name = "foodType")
	private String foodType;

	@ManyToOne
	@JoinColumn(name = "cuisineId")
	private Cuisine cuisine;

	@ManyToOne
	@JoinColumn(name = "restuarantId")
	private Restaurant restaurant;

	@ManyToMany(mappedBy = "foodList", cascade = CascadeType.ALL)
	private List<MyOrder> orderList;

	public Food() {
		super();
	}

	public Food(int foodId, String foodName, double price, String foodStatus, Cuisine cuisine, Restaurant restaurant) {
		super();
		this.foodId = foodId;
		this.foodName = foodName;
		this.price = price;
		this.foodStatus = foodStatus;
		this.cuisine = cuisine;
		this.restaurant = restaurant;
	}

	/**
	 * @return the food id
	 */
	public int getFoodId() {
		return foodId;
	}

	/**
	 * @param foodId to set the food id
	 */
	public void setFoodId(int foodId) {
		this.foodId = foodId;
	}

	/**
	 * @return the food name
	 */
	public String getFoodName() {
		return foodName;
	}

	/**
	 * @param foodName to set the food name
	 */
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @param price to set the price
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * @param foodStatus to set the foodStatus
	 */

	public void setFoodStatus(String foodStatus) {
		this.foodStatus = foodStatus;
	}

	/**
	 * @return the restaurant entity
	 */
	public Restaurant getRestaurant() {
		return restaurant;
	}

	/**
	 * @param restaurant to set the restaurant
	 */
	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	/**
	 * @return the cuisine
	 */
	public Cuisine getCuisine() {
		return cuisine;
	}

	/**
	 * @param cuisine the cuisine to set
	 */
	public void setCuisine(Cuisine cuisine) {
		this.cuisine = cuisine;
	}

	/**
	 * @return the foodStatus
	 */
	public String getFoodStatus() {
		return foodStatus;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Food [foodId=" + foodId + ", foodName=" + foodName + ", price=" + price + ", foodStatus=" + foodStatus
				+ ", cuisine=" + cuisine + ", restaurant=" + restaurant + "]";
	}

}
