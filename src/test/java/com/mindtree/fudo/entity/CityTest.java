package com.mindtree.fudo.entity;

import static org.junit.Assert.assertEquals;


import org.junit.Test;

import com.mindtree.fudo.dto.CityDTO;

public class CityTest {
	@Test
	public void test() {
City city=new City();
city.setCityId(1);
city.setCityName("Chennai");

assertEquals(city.getCityId(),1);
assertEquals(city.getCityName(),"Chennai");

}
	@Test
	public void testForConstructor()
	{
City cityDto=new City(1,"Chennai");
	assertEquals(cityDto.getCityId(),1);
	assertEquals(cityDto.getCityName(),"Chennai");


}
}
