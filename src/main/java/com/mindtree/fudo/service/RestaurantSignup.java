package com.mindtree.fudo.service;

import java.util.List;

import com.mindtree.fudo.entity.Restaurant;
import com.mindtree.fudo.exceptions.MyApplicationException;

public interface RestaurantSignup {

	List<Restaurant> getAll();

	Restaurant addRestaurant(Restaurant resObj) throws MyApplicationException;

	//boolean setImage(String imageAsString) throws MyApplicationException;

}
