package com.mindtree.fudo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mindtree.fudo.entity.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {

}
