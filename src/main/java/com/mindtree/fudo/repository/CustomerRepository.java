package com.mindtree.fudo.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mindtree.fudo.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	@Transactional
	@Modifying
	@Query("update Customer  set points = ?2 where customerEmail=?1 ")
	void update(String customerEmail, int coin);

}
