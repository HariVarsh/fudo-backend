package com.mindtree.fudo.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.mindtree.fudo.entity.City;
import com.mindtree.fudo.exceptions.MyApplicationException;
import com.mindtree.fudo.exceptions.RecordNotFoundException;
import com.mindtree.fudo.repository.CityRepository;

@RunWith(MockitoJUnitRunner.Silent.class)
public class CityServiceImplTest {

	@InjectMocks
	private CityServiceImpl cityService;
	@Mock

	private CityRepository cityRepository;

	@Test
	public void testGetallcity() throws MyApplicationException {
		List<City> citylist = new ArrayList<>();
		City city = new City(1, "Chennai");
		citylist.add(city);
		City city1 = new City(2, "Hyderbad");
		citylist.add(city1);

		Mockito.when(cityRepository.findAll()).thenReturn(citylist);

		assertEquals(citylist, cityService.getAllCity());

	}

	@Test
	public void testAddCity() {
		City city = new City(1, "Chennai");

		cityService.addCity("Chennai");
	}

	@Test

	public void testGetallcityException() {
		List<City> citylist = new ArrayList<>();

		try {

			Mockito.when(cityRepository.findAll()).thenReturn(citylist);

			assertEquals(citylist, cityService.getAllCity());
		} catch (RecordNotFoundException e) {
			System.out.println(e.getMessage());
		}

	}

}
