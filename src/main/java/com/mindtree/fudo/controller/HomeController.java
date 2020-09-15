package com.mindtree.fudo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.fudo.dto.CityDTO;
import com.mindtree.fudo.entity.City;
import com.mindtree.fudo.exceptions.MyApplicationException;
import com.mindtree.fudo.service.CityService;
import com.mindtree.fudo.service.OrderService;

@CrossOrigin
@RestController
@RequestMapping("/fudo/home")
public class HomeController {

	@Autowired
	CityService cityService;

	@GetMapping("/")
	
	/*
	 * Rest controller to get all the city names
	 */
	public ResponseEntity<?> display() {
		Map<String,Object> responseheaders=new HashMap<String,Object>();
		
		try {

			
			List<City> cityList = cityService.getAllCity();
			
			List<CityDTO> cityDtoList = new ArrayList();
			cityList.forEach((e -> {
				CityDTO cityDtoObject = new CityDTO();
				cityDtoObject.setCityId(e.getCityId());
				cityDtoObject.setCityName(e.getCityName());

				cityDtoList.add(cityDtoObject);
			}));

			return  ResponseEntity.status(HttpStatus.OK).body(cityDtoList);
		} catch (MyApplicationException e) {
			
			
			return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		catch(Exception e) {
			return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
				

		}

	}
}