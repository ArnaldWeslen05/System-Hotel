package com.arnaldwelen.SystemHotel.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arnaldwelen.SystemHotel.entites.Payment;
import com.arnaldwelen.SystemHotel.repository.PaymentRepository;

@Service
public class PaymentService {

	@Autowired
	private PaymentRepository payment;
	
	public List<Payment> findAll(){
		return payment.findAll();
	}
	
	public Payment findById(Long Id ) {
		Optional<Payment> obj = payment.findById(Id);
		return obj.get();
	}
}
