package com.mindtree.fudo.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "restaurant")
public class Restaurant {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "restaurantId", nullable = false, unique = true)
	private int restaurantId;
	@Column(name = "ownerName", nullable = false)
	private String ownerName;
	@Column(name = "ownerEmail", nullable = false, unique = true)
	private String ownerEmail;
	@Column(name = "ownerPhone", nullable = false, unique = true)
	private long ownerPhone;
	@Column(name = "restaurantPassword", nullable = false)
	private String restaurantPassword;
	@Column(name = "restaurantAddress", nullable = false)
	private String restaurantAddress;
	@Column(name = "restaurantName", nullable = false)
	private String restaurantName;
	@Column(name = "restaurantCity", nullable = false)
	private String restaurantCity;
	@Column(name = "restaurantStatus")
	private String restaurantStatus;

	@Column (name="imageUrl")
	@Length(max = 100000000)

	private String imageUrl;
	@Column(name = "averageRating")
	private double averageRating;

	
	@OneToMany(mappedBy = "restaurant")
	private List<MyOrder> orderList;

	@ManyToOne
	private City city;

	public Restaurant(int restaurantId, String ownerName, String ownerEmail, long ownerPhone, String restaurantPassword,
			String restaurantAddress, String restaurantName, String restaurantCity, String restaurantStatus,
			String imageUrl, double averageRating, City city) {
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
		this.city = city;
	}

	public Restaurant() {
		super();
	}

	@Override
	public String toString() {
		return "Restaurant [restaurantId=" + restaurantId + ", ownerName=" + ownerName + ", ownerEmail=" + ownerEmail
				+ ", ownerPhone=" + ownerPhone + ", restaurantPassword=" + restaurantPassword + ", restaurantAddress="
				+ restaurantAddress + ", restaurantName=" + restaurantName + ", restaurantCity=" + restaurantCity
				+ ", restaurantStatus=" + restaurantStatus + ", imageUrl=" + imageUrl + ", averageRating="
				+ averageRating + ", city=" + city + "]";
	}

	public double getAverageRating() {
		return averageRating;
	}

	public void setAverageRating(double averageRating) {
		this.averageRating = averageRating;
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

	public void setRestaurantStatus(String restaurantStatus) {
		this.restaurantStatus = restaurantStatus;
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

	public String getRestaurantStatus() {
		return restaurantStatus;
	}

	/**
	 * @return the city
	 */
	public City getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(City city) {
		this.city = city;
	}
}
