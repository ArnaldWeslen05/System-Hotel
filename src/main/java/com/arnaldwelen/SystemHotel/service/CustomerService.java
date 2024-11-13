package com.arnaldwelen.SystemHotel.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arnaldwelen.SystemHotel.entites.Customer;
import com.arnaldwelen.SystemHotel.repository.CustomerRepository;

@Service
public class CustomerService {

	
	@Autowired
	private CustomerRepository customer;
	
	public List<Customer> findAll(){
		return customer.findAll();
	}
	
	public Customer findById(Long id) {
		Optional<Customer> obj = customer.findById(id);
		return obj.get();
	}
	
	
}
