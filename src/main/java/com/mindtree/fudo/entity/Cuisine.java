package com.mindtree.fudo.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "cuisine")
public class Cuisine {

	@Id
	@Column(name = "cuisineId", nullable = false, unique = true)
	int cuisineId;

	@Column(name = "cuisineName", nullable = false, unique = true)
	String cuisineName;

	@OneToMany(mappedBy = "cuisine")
	private List<Food> food;

	public Cuisine() {
		super();
	}

	public Cuisine(int cuisineId, String cuisineName) {
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
