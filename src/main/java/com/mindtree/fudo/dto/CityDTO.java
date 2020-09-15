package com.mindtree.fudo.dto;

import java.util.List;

import com.mindtree.fudo.entity.Restaurant;

public class CityDTO {

		private int cityId;
		private String cityName;
		private List<Restaurant> restaurantList;

		public CityDTO() {
			super();
			// TODO Auto-generated constructor stub
		}

		public CityDTO(int cityId, String cityName) {
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

		public List<Restaurant> getRestaurantList() {
			return restaurantList;
		}

		public void setRestaurantList(List<Restaurant> restaurantList) {
			this.restaurantList = restaurantList;
		}

		
}
