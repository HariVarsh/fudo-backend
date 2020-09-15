	package com.mindtree.fudo.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="city")
public class City {

		
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="cityId",nullable=false,unique=true)
	private int cityId;
	@Column(name="cityName",nullable=false,unique=true)
	private String cityName;
	
	@OneToMany(mappedBy="city",cascade = CascadeType.ALL)
	private List<Restaurant> restaurantList;

	public City() {
		super();
	}

	public City(int cityId, String cityName) {
		super();
		this.cityId = cityId;
		this.cityName = cityName;

	}

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
}
