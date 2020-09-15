package com.mindtree.fudo.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mindtree.fudo.entity.MyOrder;

@Repository
public interface OrderRepository extends JpaRepository<MyOrder,Integer> {

	

}
