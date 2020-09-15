package com.mindtree.fudo.dto;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.mindtree.fudo.entity.City;
import com.mindtree.fudo.entity.Food;

public class RestaurantDTO {

	private int restaurantId;
	
	
	@NotBlank(message="Name is Mandatory")
	@NotNull(message="Name cannot be null")
	@Pattern(message="Special characters not allowed",regexp="^[a-zA-Z]+$")
	private String ownerName;
	
	@Email(message="not a valid email")
	private String ownerEmail;
	
	private long ownerPhone;
	
	@NotBlank(message = "Restaurant Password is mandatory")
	@NotNull(message="Restaurant Password  cannot be null")
	@Pattern(message="Password should contain uppercase,lowecase,number and length between 6 to 10", regexp="^((?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])).{6,10}$")
	private String restaurantPassword;
	
	@NotBlank(message="Restaurant Address is mandatory")
	@NotNull(message="Restaurant Address cannot be null")
	@Size(max=50,message="Address too long")
	private String restaurantAddress;
	
	@NotBlank(message="Restaurant Name is mandatory")
	@NotNull(message="Restaurant Name cannot be null")
	@Size(max=15,message="Restaurant Name too long")
	private String restaurantName;
	
	@NotBlank(message="Restaurant City is mandatory")
	@NotNull(message="Restaurant City cannot be null")
	private String restaurantCity;
	
	private String restaurantStatus;
	
	private String imageUrl;
	
	private Double averageRating;
	
	private List<Food> foodList;
	
	private City city;

	public RestaurantDTO() {
		super();
	}

	public RestaurantDTO(int restaurantId, String ownerName, String ownerEmail, long ownerPhone,
			String restaurantPassword, String restaurantAddress, String restaurantName, String restaurantCity,
			String restaurantStatus, String imageUrl, Double averageRating, City city) {
		super();
		this.restaurantId = restaurantId;
		this.ownerName = ownerName;
		this.ownerEmail = ownerEmail;
		this.ownerPhone = ownerPhone;
		this.restaurantPassword = restaurantPassword;
		this.restaurantAddress = restaurantAddress;
		this.restaurantName = restaurantName;
		this.restaurantCity = restaurantCity;
		this.restaurantStatus = restaurantStatus;
		this.imageUrl = imageUrl;
		this.averageRating = averageRating;
		//this.city = city;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getOwnerEmail() {
		return ownerEmail;
	}

	public void setOwnerEmail(String ownerEmail) {
		this.ownerEmail = ownerEmail;
	}

	public long getOwnerPhone() {
		return ownerPhone;
	}

	public void setOwnerPhone(long ownerPhone) {
		this.ownerPhone = ownerPhone;
	}

	public String getRestaurantPassword() {
		return restaurantPassword;
	}

	public void setRestaurantPassword(String restaurantPassword) {
		this.restaurantPassword = restaurantPassword;
	}

	public String getRestaurantAddress() {
		return restaurantAddress;
	}

	public void setRestaurantAddress(String restaurantAddress) {
		this.restaurantAddress = restaurantAddress;
	}

	public String getRestaurantName() {
		return restaurantName;
	}

	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}

	public String getRestaurantCity() {
		return restaurantCity;
	}

	public void setRestaurantCity(String restaurantCity) {
		this.restaurantCity = restaurantCity;
	}

	/**
	 * @return the restaurantStatus
	 */
	public String getRestaurantStatus() {
		return restaurantStatus;
	}

	public void setRestaurantStatus(String restaurantStatus) {
		this.restaurantStatus = restaurantStatus;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public Double getAverageRating() {
		return averageRating;
	}

	public void setAverageRating(Double averageRating) {
		this.averageRating = averageRating;
	}

	public int getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	@Override
	public String toString() {
		return "RestaurantDTO [restaurantId=" + restaurantId + ", ownerName=" + ownerName + ", ownerEmail=" + ownerEmail
				+ ", ownerPhone=" + ownerPhone + ", restaurantPassword=" + restaurantPassword + ", restaurantAddress="
				+ restaurantAddress + ", restaurantName=" + restaurantName + ", restaurantCity=" + restaurantCity
				+ ", restaurantStatus=" + restaurantStatus + ", imageUrl=" + imageUrl + ", averageRating="
				+ averageRating + ", foodList=" + foodList + ", city=" + city + "]";
	}

}
