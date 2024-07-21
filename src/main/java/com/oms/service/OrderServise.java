package com.oms.service;

import java.util.List;

import com.oms.model.Order;

public interface OrderServise {
	Order createOrder(Order order);

	Order getOrderById(Integer id);

	List<Order> getAllOrders();
	
	List<Order> getOrdersByCustomerId(Integer customerId);
}
