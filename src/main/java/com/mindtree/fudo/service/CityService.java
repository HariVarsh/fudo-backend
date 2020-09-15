package com.mindtree.fudo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mindtree.fudo.entity.City;
import com.mindtree.fudo.exceptions.MyApplicationException;

@Service
public interface CityService {

	List<City> getAllCity()  throws MyApplicationException;
	void addCity(String cityName);
}
