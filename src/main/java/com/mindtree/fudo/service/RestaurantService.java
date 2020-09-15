package com.mindtree.fudo.service;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.mindtree.fudo.entity.Restaurant;
import com.mindtree.fudo.exceptions.RecordNotFoundException;

@Service
public interface RestaurantService {

	Set<Restaurant> getRestaurant(int cityId) throws RecordNotFoundException;
}
