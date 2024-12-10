package com.arnaldwelen.SystemHotel.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arnaldwelen.SystemHotel.entites.Consumption;
import com.arnaldwelen.SystemHotel.repository.ConsumptionRepository;

import jakarta.persistence.EntityNotFoundException;

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
	
	 public Consumption insert(Consumption obj) {
	        return consumption.save(obj);
	    }

	    public void delete(Long id) {
	        if (!consumption.existsById(id)) {
	            throw new EntityNotFoundException("Quarto não encontrado com id: " + id);
	        }
	        consumption.deleteById(id);
	    }

	    public Consumption update(Long id, Consumption obj) {
	        try {
	        	Consumption entity = consumption.getReferenceById(id);
	            updateData(entity, obj);
	            return consumption.save(entity);
	        } catch (EntityNotFoundException e) {
	            throw new EntityNotFoundException("Quarto não encontrado com id: " + id);
	        }
	    }

	    private void updateData(Consumption entity, Consumption obj) {
	        entity.setDate(obj.getDate());;
	        entity.setQuantity(obj.getQuantity());;
	        entity.setProduct(obj.getProduct());
	        entity.setReservation(obj.getReservation());
	    }
	
}
