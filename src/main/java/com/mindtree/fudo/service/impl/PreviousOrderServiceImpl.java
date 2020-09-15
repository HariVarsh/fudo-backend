package com.mindtree.fudo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.fudo.entity.MyOrder;
import com.mindtree.fudo.exceptions.MyApplicationException;
import com.mindtree.fudo.exceptions.RecordNotFoundException;
import com.mindtree.fudo.repository.OrderRepository;
import com.mindtree.fudo.service.PreviousOrders;

@Service
public class PreviousOrderServiceImpl implements PreviousOrders{
	
	@Autowired
	OrderRepository orderRepository;

	//Getting all the previous orders done by customer using customer Id
	
	@Override
	public List<MyOrder> getAllOrders(int customerId) throws MyApplicationException {

			List<MyOrder> orders = new ArrayList<MyOrder>();
			List<MyOrder> order;
			try {
				order = orderRepository.findAll();
				for (MyOrder c : order) {
					if (c.getCustomer().getCustomerId() == customerId) {
						orders.add(c);
					}
				}
				if (order == null) {
					throw new RecordNotFoundException("No orders placed by customer");
				}
			} catch (RecordNotFoundException e) {
				throw new MyApplicationException("No order record found", e);
			}

			return orders;
	}
}
