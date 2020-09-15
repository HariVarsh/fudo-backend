package com.mindtree.fudo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.fudo.entity.City;
import com.mindtree.fudo.exceptions.MyApplicationException;
import com.mindtree.fudo.exceptions.RecordNotFoundException;
import com.mindtree.fudo.repository.CityRepository;
import com.mindtree.fudo.service.CityService;

@Service
public class CityServiceImpl implements CityService {

	@Autowired
	CityRepository cityRespository;

	@Override
	
/*
 * Get the all cities from the city table
 */
	public List<City> getAllCity() throws RecordNotFoundException  {
		try {
			List<City> cityList =new ArrayList<>();
					cityList=cityRespository.findAll();
			if (cityList.isEmpty()) {
				throw new RecordNotFoundException("There is no city in database");
			} else {
				return cityList;
			}
		} catch (RecordNotFoundException e) {
			
			throw new RecordNotFoundException(e.getMessage());
		}
	}
	
	/*
	 *Insert the city in if it is not present in the database
	 */
	public void addCity(String cityName) {
		City city=new City();
		city.setCityName(cityName);
		cityRespository.save(city);
		
	}

}
