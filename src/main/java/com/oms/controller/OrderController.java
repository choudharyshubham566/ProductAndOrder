package com.oms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oms.model.Order;
import com.oms.service.OrderServise;

@RestController
@RequestMapping("/Order")
public class OrderController {

	@Autowired
	private OrderServise orderServise;

	@PostMapping("/addOrder")
	public ResponseEntity<Order> addOrder(@RequestBody Order order) {
		Order newOrder = orderServise.createOrder(order);
		return new ResponseEntity<Order>(newOrder, HttpStatus.CREATED);
	}

	@GetMapping("/getOrder/{id}")
	public ResponseEntity<Order> getOrder(@PathVariable Integer id) {
		Order order = orderServise.getOrderById(id);
		return new ResponseEntity<Order>(order, HttpStatus.OK);
	}

	@GetMapping("/getOrders")
	public ResponseEntity<List<Order>> getAllOrders() {
		List<Order> orders = orderServise.getAllOrders();
		return new ResponseEntity<List<Order>>(orders, HttpStatus.OK);
	}

	@GetMapping("/getOrders/{customerId}")
	public ResponseEntity<List<Order>> getOrdersByCustomerId(@PathVariable Integer customerId) {
		List<Order> orders = orderServise.getOrdersByCustomerId(customerId);
		return new ResponseEntity<List<Order>>(orders, HttpStatus.OK);
	}

}
