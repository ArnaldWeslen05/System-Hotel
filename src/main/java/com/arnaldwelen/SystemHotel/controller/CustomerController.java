package com.arnaldwelen.SystemHotel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arnaldwelen.SystemHotel.entites.Customer;
import com.arnaldwelen.SystemHotel.service.CustomerService;

@RestController
@RequestMapping(value = "/customer")
public class CustomerController {

	@Autowired
	private CustomerService service;
	
	@GetMapping
	public ResponseEntity<List<Customer>> findAll(){
		List<Customer> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Customer> findById(@PathVariable Long Id){
		Customer obj = service.findById(Id);
		return ResponseEntity.ok().body(obj);
	}
}
