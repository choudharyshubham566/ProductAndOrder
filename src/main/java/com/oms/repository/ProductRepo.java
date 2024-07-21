package com.oms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oms.model.Product;

public interface ProductRepo extends JpaRepository<Product, Integer>{

}
