package com.mindtree.fudo.dto;

import java.util.List;

import com.mindtree.fudo.entity.Food;

public class CuisineDTO {

	private int cuisineId;
	private String cuisineName;
	private List<Food> food;

	public CuisineDTO() {
		super();
	}

	public CuisineDTO(int cuisineId, String cuisineName) {
		super();
		this.cuisineId = cuisineId;
		this.cuisineName = cuisineName;
	}

	public int getCuisineId() {
		return cuisineId;
	}

	public void setCuisineId(int cuisineId) {
		this.cuisineId = cuisineId;
	}

	public String getCuisineName() {
		return cuisineName;
	}

	public void setCuisineName(String cuisineName) {
		this.cuisineName = cuisineName;
	}

	@Override
	public String toString() {
		return "Cuisine [cuisineId=" + cuisineId + ", cuisineName=" + cuisineName + "]";
	}
}
