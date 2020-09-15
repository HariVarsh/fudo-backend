package com.mindtree.fudo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mindtree.fudo.entity.Food;

public interface FoodRepository extends JpaRepository<Food, Integer> {
	
	Integer deleteById(int id);

}
