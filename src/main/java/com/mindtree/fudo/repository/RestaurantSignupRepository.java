package com.mindtree.fudo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mindtree.fudo.entity.Restaurant;

@Repository
public interface RestaurantSignupRepository extends JpaRepository<Restaurant,Integer> {

	@Modifying
	@Transactional
	@Query("UPDATE Restaurant r SET r.imageUrl = ?1 where r.restaurantId = 1")
	void updatePhoto(String imageAsString);


}
