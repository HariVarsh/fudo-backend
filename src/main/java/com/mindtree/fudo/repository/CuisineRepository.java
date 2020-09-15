package com.mindtree.fudo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mindtree.fudo.entity.Cuisine;

public interface CuisineRepository extends JpaRepository<Cuisine, Integer> {

}
