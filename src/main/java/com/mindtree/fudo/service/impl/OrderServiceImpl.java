package com.mindtree.fudo.service.impl;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.fudo.entity.Cart;
import com.mindtree.fudo.entity.Customer;
import com.mindtree.fudo.entity.MyOrder;
import com.mindtree.fudo.exceptions.InvalidDataException;
import com.mindtree.fudo.exceptions.MyApplicationException;
import com.mindtree.fudo.exceptions.RecordNotFoundException;
import com.mindtree.fudo.repository.CartRepository;
import com.mindtree.fudo.repository.CustomerRepository;
import com.mindtree.fudo.repository.FoodRepository;
import com.mindtree.fudo.repository.OrderRepository;
import com.mindtree.fudo.repository.RestaurantRepository;
import com.mindtree.fudo.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderRepository orderRepository;
	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	FoodRepository foodRepository;
	@Autowired
	RestaurantRepository restaurantRepository;
	@Autowired
	CartRepository cartRepository;

	/*inserts the order data into database
	 * (non-Javadoc)
	 * @see com.mindtree.fudo.service.OrderService#placeOrder(com.mindtree.fudo.entity.MyOrder)
	 */
	@Override
	public void placeOrder(MyOrder orders) throws MyApplicationException {
		Customer customer = new Customer();
		Date today = new Date(System.currentTimeMillis());
		orders.setOrderDate(today);
		System.out.println(today);

		try {
			if (orders.getAddress().length() < 25 || orders.getAddress() == null) {
				throw new InvalidDataException("this address is invalid");
			}
			/*
			 * customer email is passed from front end, but db expects customer id, so the
			 * customer id of the user entered mail id is found here
			 */
			List<Customer> clist = customerRepository.findAll().stream()
					.filter(i -> i.getCustomerEmail().equalsIgnoreCase(orders.getCustomer().getCustomerEmail()))
					.collect(Collectors.toList());

			if (clist.size() == 0) {
				throw new RecordNotFoundException("this customer doesnot exists");
			}
			customer.setCustomerId(clist.get(0).getCustomerId());
			orders.setCustomer(customer);

			// to check if restaurant id exists in db or not
			if (!restaurantRepository.findAll().stream()
					.anyMatch(i -> i.getRestaurantId() == orders.getRestaurant().getRestaurantId())) {
				throw new RecordNotFoundException("this restaurant doesn't exist");
			}

			orderRepository.save(orders);
		} catch (RecordNotFoundException e) {
			throw new MyApplicationException(e.getMessage());
		} catch (InvalidDataException e) {
			throw new MyApplicationException(e.getMessage());
		}
	}

	
	/*adds item to the cart table
	 * (non-Javadoc)
	 * @see com.mindtree.fudo.service.OrderService#addToCart(com.mindtree.fudo.entity.Cart)
	 */
	@Override
	public void addToCart(Cart cart) throws MyApplicationException {
		try {
			// check if customer exists in db
			List<Customer> clist = customerRepository.findAll().stream()
					.filter(i -> i.getCustomerEmail().equals(cart.getCustomerEmail())).collect(Collectors.toList());
			System.out.println(clist);
			if (clist.size() == 0) {
				throw new RecordNotFoundException(
						"this customer doesnot exists please sign-in, if You already have an account please log-in");
			}
			// check if foodId exists in db
			if (!foodRepository.findAll().stream().anyMatch(i -> i.getFoodId() == cart.getFoodId()))
				throw new RecordNotFoundException("this foodId doesnot exists");

			// check if foodName exists in db
			if (!foodRepository.findAll().stream().anyMatch(i -> i.getFoodName().equals(cart.getFoodName())))
				throw new RecordNotFoundException("this foodName doesnot exists");

			// check if price exists in db
			if (!foodRepository.findAll().stream().anyMatch(i -> i.getPrice() == cart.getPrice()))
				throw new RecordNotFoundException("this price doesnot exists");

			// to check if restaurant id exists in db or not
			if (!restaurantRepository.findAll().stream().anyMatch(i -> i.getRestaurantId() == cart.getRestaurantId())) {
				throw new RecordNotFoundException("this restaurant doesn't exist");
			}
		} catch (RecordNotFoundException e) {
			throw new MyApplicationException(e);
		}

		cartRepository.save(cart);
	}

	/* delete the given food Id from the cart table
	 * (non-Javadoc)
	 * @see com.mindtree.fudo.service.OrderService#deleteFromCartById(java.lang.String, int)
	 */
	@Override
	public void deleteFromCartById(String customerEmail, int foodId) throws MyApplicationException {

		try {
			// check if customer exists in db
			List<Customer> clist = customerRepository.findAll().stream()
					.filter(i -> i.getCustomerEmail().equals(customerEmail)).collect(Collectors.toList());

			if (clist.size() == 0) {
				throw new RecordNotFoundException("this customer doesnot exists");
			}

			// check if foodId exists in db
			if (!foodRepository.findAll().stream().anyMatch(i -> i.getFoodId() == foodId))
				throw new RecordNotFoundException("this foodId doesnot exists");
		} catch (RecordNotFoundException e) {
			throw new MyApplicationException(e.getMessage());
		}

		int count = 0, cartId = 0;
		// getting the row of the food Id which is equal to the newly coming foodid to be
		// added
		List<Cart> cartList = cartRepository.findAll().stream()
				.filter(i -> i.getCustomerEmail().equals(customerEmail) && i.getFoodId() == foodId)
				.collect(Collectors.toList());

		for (Cart c : cartList) {

			count = c.getCount();
			cartId = c.getCartId();
		}
		if (count != 1) {
			count--;

			cartRepository.update(cartId, count);
		}

		else
			cartRepository.deleteThatRow(cartId, count);

	}

	/*deleting the entire cart
	 * (non-Javadoc)
	 * @see com.mindtree.fudo.service.OrderService#deleteAll(java.lang.String)
	 */
	@Override
	public void deleteAll(String customerEmail) throws MyApplicationException {
		try {
			// check if customer exists in db
			List<Customer> clist = customerRepository.findAll().stream()
					.filter(i -> i.getCustomerEmail().equals(customerEmail)).collect(Collectors.toList());

			if (clist.size() == 0) {
				throw new RecordNotFoundException("this customer doesnot exists");
			}

		} catch (RecordNotFoundException e) {
			throw new MyApplicationException(e.getMessage());
		}
		cartRepository.deleteFullCart(customerEmail);
	}

	/*displaying the cart table's content
	 * (non-Javadoc)
	 * @see com.mindtree.fudo.service.OrderService#displayCart(java.lang.String)
	 */
	@Override
	public List<Cart> displayCart(String customerEmail) {

		int flag = 0;
		List<Cart> cartList = cartRepository.findAll().stream().filter(i -> i.getCustomerEmail().equals(customerEmail))
				.collect(Collectors.toList());
		if (cartList.size() != 0)
			return cartList;
		else
			return null;

	}

	/*search the cart for the availability of the item
	 * (non-Javadoc)
	 * @see com.mindtree.fudo.service.OrderService#searchCart(java.lang.String, int)
	 */
	@Override
	public boolean searchCart(String customerEmail, int foodId) {
		int count = 0, cartId = 0;
		int flag = 0;
		if (cartRepository.findAll().stream()
				.anyMatch(i -> i.getCustomerEmail().equals(customerEmail) && i.getFoodId() == foodId)) {
			List<Cart> cartList = cartRepository.findAll().stream()
					.filter(i -> i.getCustomerEmail().equals(customerEmail) && i.getFoodId() == foodId)
					.collect(Collectors.toList());

			for (Cart c : cartList) {

				count = c.getCount();
				cartId = c.getCartId();
			}
			count++;
			cartRepository.update(cartId, count);
			flag = 1;
		}

		if (flag == 0) {
			return false;
		} else
			return true;
	}

	/*after the payment coins are updated
	 * (non-Javadoc)
	 * @see com.mindtree.fudo.service.OrderService#updateCoins(java.lang.String, int)
	 */
	@Override
	public void updateCoins(String customerEmail, int coin) throws MyApplicationException {
		try {
			// check if customer exists in db
			List<Customer> clist = customerRepository.findAll().stream()
					.filter(i -> i.getCustomerEmail().equals(customerEmail)).collect(Collectors.toList());

			if (clist.size() == 0) {
				throw new RecordNotFoundException("this customer doesnot exists");
			}
			customerRepository.update(customerEmail, coin);

		} catch (RecordNotFoundException e) {
			throw new MyApplicationException(e.getMessage());
		}

	}

	/* getting the available coins from the table
	 * (non-Javadoc)
	 * @see com.mindtree.fudo.service.OrderService#getCoins(java.lang.String)
	 */
	@Override
	public Object getCoins(String customerEmail) throws MyApplicationException {

		// check if customer exists in db
		List<Customer> clist = customerRepository.findAll().stream()
				.filter(i -> i.getCustomerEmail().equals(customerEmail)).collect(Collectors.toList());
		try {
			if (clist.size() == 0) {
				throw new RecordNotFoundException("this customer doesnot exists");
			} else {

			}

		} catch (RecordNotFoundException e) {
		
			throw new MyApplicationException(e.getMessage());
		}

		return clist.get(0).getPoints();
	}

}
