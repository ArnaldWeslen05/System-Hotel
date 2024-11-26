package com.arnaldwelen.SystemHotel.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arnaldwelen.SystemHotel.entites.Product;
import com.arnaldwelen.SystemHotel.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository product;
	
	
	public List<Product> findAll(){
		return product.findAll();
	}
	
	public Product findById(Long id) {
		Optional<Product> obj = product.findById(id);
		return obj.get();
	}
}
