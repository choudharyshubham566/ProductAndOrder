package com.oms.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oms.annotation.LogOms;
import com.oms.model.Order;
import com.oms.model.Product;
import com.oms.repository.OrderRepo;
import com.oms.repository.ProductRepo;

@Service
public class OrderServiceImpl implements OrderServise {

	@Autowired
	private OrderRepo orderRepo;

	@Autowired
	private ProductRepo productRepo;

	@LogOms
	@Override
	public Order createOrder(Order order) {

		Product product = productRepo.findById(order.getProduct().getProductId())
				.orElseThrow(() -> new RuntimeException("Product doesn't exist"));

		order.setUnitPrice(product.getPrice());
		int quantity = order.getQuantity();

		// Update the product stock on creating the order
		product.setStock(product.getStock() - quantity);
		productRepo.save(product);

		BigDecimal total = BigDecimal.valueOf(quantity).multiply(BigDecimal.valueOf(order.getUnitPrice()));
		order.setTotal(total);
		return orderRepo.save(order);
	}

	@LogOms
	@Override
	public Order getOrderById(Integer id) {
		return orderRepo.findById(id).get();

	}

	@LogOms
	@Override
	public List<Order> getAllOrders() {
		return orderRepo.findAll();
	}

	@LogOms
	@Override
	public List<Order> getOrdersByCustomerId(Integer customerId) {

		List<Order> orders = orderRepo.findOrdersByCustomerId(customerId);

		return orders.stream()
		.filter(ord -> ord.getStatus().equalsIgnoreCase("Approved"))
		.collect(Collectors.toList());

	}

}
