package com.mindtree.fudo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mindtree.fudo.entity.Cart;
import com.mindtree.fudo.entity.City;
import com.mindtree.fudo.entity.MyOrder;
import com.mindtree.fudo.exceptions.MyApplicationException;
import com.mindtree.fudo.exceptions.RecordNotFoundException;

@Service
public interface OrderService {

	void placeOrder(MyOrder myOrders) throws MyApplicationException;

	void addToCart(Cart cart) throws MyApplicationException;

	void deleteFromCartById(String customerEmail, int foodId) throws MyApplicationException;


	void deleteAll(String customerEmail) throws MyApplicationException;

	List<Cart> displayCart(String customerEmail) ;

	boolean searchCart(String customerEmail, int foodId);

	void updateCoins(String customerEmail, int coin) throws MyApplicationException;

	Object getCoins(String customerEmail) throws MyApplicationException;

}
