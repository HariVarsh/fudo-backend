package com.mindtree.fudo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.mindtree.fudo.entity.City;
import com.mindtree.fudo.entity.Restaurant;
import com.mindtree.fudo.exceptions.MyApplicationException;
import com.mindtree.fudo.exceptions.RecordNotFoundException;
import com.mindtree.fudo.repository.RestaurantSignupRepository;
import com.mindtree.fudo.service.CityService;
import com.mindtree.fudo.service.RestaurantSignup;

@Service
public class RestaurantSignupImpl implements RestaurantSignup {

	@Autowired
	CityService cityService;

	@Autowired
	RestaurantSignupRepository resRepo;

	@Override
	public List<Restaurant> getAll() {
		return resRepo.findAll();
	}

	@Override
	public Restaurant addRestaurant(Restaurant restaurantobj) throws MyApplicationException {

		try {
			List<City> allCities = cityService.getAllCity();
			int flag = 0;

			City cityObj = allCities.stream()
					.filter(city -> city.getCityName().equalsIgnoreCase(restaurantobj.getRestaurantCity())).findAny()
					.orElse(null);

			if (cityObj != null) {
				restaurantobj.setCity(cityObj);
				flag = 1;
			}

			if (flag == 0) {

				cityService.addCity(restaurantobj.getRestaurantCity());

				List<City> newCities = cityService.getAllCity();

				for (City c : newCities) {
					if (c.getCityName().equalsIgnoreCase(restaurantobj.getRestaurantCity())) {
						restaurantobj.setCity(c);
					}
				}

			}

			// restaurantobj.setCity();

			resRepo.save(restaurantobj);

			// return "Successfully Inserted";
			return restaurantobj;

		} catch (DataAccessException e) {
			throw new MyApplicationException("Repeated entry. Use a unique field", e);
		} catch (RecordNotFoundException e) {
			throw new MyApplicationException("No city");
		}
	}

	/*
	@Override
	public boolean setImage(String imageAsString) throws MyApplicationException {
		try {
			resRepo.updatePhoto(imageAsString);
			return true;
		} catch (DataAccessException e) {
			throw new MyApplicationException("Error in DB", e);
		}
	}
*/
}
