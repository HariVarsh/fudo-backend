package com.mindtree.fudo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.fudo.entity.MyOrder;
import com.mindtree.fudo.exceptions.MyApplicationException;
import com.mindtree.fudo.exceptions.RecordNotFoundException;
import com.mindtree.fudo.repository.OrderRepository;
import com.mindtree.fudo.service.RestaurantsOrdersService;

@Service
public class RestaurantsOrdersServiceImpl implements RestaurantsOrdersService  {

	@Autowired
	OrderRepository oRepository;

	@Override
	public List<MyOrder> getOrders(int restaurantId) throws MyApplicationException {
		
		List<MyOrder> orders=new ArrayList<MyOrder>();
		List<MyOrder> order=null;
		try {
			order=oRepository.findAll();
			for(MyOrder o:order)
			{
				if(o.getRestaurant().getRestaurantId()==restaurantId)
				{
					orders.add(o);
				}
			}
			if(order==null)
			{
				throw new RecordNotFoundException("Restaurant has no orders");
			}
		}catch(RecordNotFoundException e)
		{
			throw new MyApplicationException("No restaurant record found", e);
		}
		return orders;
	}

}
