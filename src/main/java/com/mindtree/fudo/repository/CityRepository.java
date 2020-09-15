package com.mindtree.fudo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.fudo.entity.City;

@Repository
public interface CityRepository  extends JpaRepository<City, Integer> {

}
