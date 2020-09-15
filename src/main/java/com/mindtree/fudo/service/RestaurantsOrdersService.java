package com.mindtree.fudo.service;

import java.util.List;

import com.mindtree.fudo.entity.MyOrder;
import com.mindtree.fudo.exceptions.MyApplicationException;

public interface RestaurantsOrdersService {
	
	List<MyOrder> getOrders(int restaurantId) throws MyApplicationException;


}
