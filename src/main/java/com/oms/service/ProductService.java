package com.oms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oms.annotation.LogOms;
import com.oms.model.Product;
import com.oms.repository.ProductRepo;

@Service
public class ProductService {

	@Autowired
	private ProductRepo productRepo;

	@LogOms
	public Product createProduct(Product product) {
		return productRepo.save(product);
	}

	@LogOms
	public Product getProductById(Integer id) {
		return productRepo.findById(id).orElseThrow(() -> new RuntimeException("Product does not exist"));
	}

}
