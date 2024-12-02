package com.arnaldwelen.SystemHotel.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arnaldwelen.SystemHotel.entites.Consumption;
import com.arnaldwelen.SystemHotel.repository.ConsumptionRepository;

@Service
public class ConsumptionService {

	@Autowired
	private ConsumptionRepository consumption;
	
	public List<Consumption> findAll(){
		return consumption.findAll();
	}
	
	public  Consumption findById(Long id) {
		Optional<Consumption> obj = consumption.findById(id);
		return obj.get();
	}
	
}
