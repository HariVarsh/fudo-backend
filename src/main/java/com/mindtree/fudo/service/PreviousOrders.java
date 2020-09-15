package com.mindtree.fudo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mindtree.fudo.entity.MyOrder;
import com.mindtree.fudo.exceptions.MyApplicationException;

@Service
public interface PreviousOrders {
	//function for getting all the previous orders
	List<MyOrder> getAllOrders(int customerId)throws MyApplicationException;

}
