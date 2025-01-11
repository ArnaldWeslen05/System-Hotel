package com.arnaldwelen.SystemHotel.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arnaldwelen.SystemHotel.entites.Customer;
import com.arnaldwelen.SystemHotel.repository.CustomerRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customer;

	public List<Customer> findAll() {
		return customer.findAll();
	}

	public Customer findById(Long id) {
		Optional<Customer> obj = customer.findById(id);
		return obj.orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado com id: " + id));
	}

	public Customer insert(Customer obj) {
		return customer.save(obj);
	}

	public void delete(Long id) {
		if (!customer.existsById(id)) {
			throw new EntityNotFoundException("cliente não encontrado com id : " + id);
		}
		customer.deleteById(id);
	}

	public Customer update(Long id, Customer obj) {
		try {
			Customer entity = customer.getReferenceById(id);
			updateData(entity, obj);
			return customer.save(entity);

		} catch (EntityNotFoundException e) {
			throw new EntityNotFoundException("Cliente não encontrado com id: " + id);

		}

	}
	
	
	private void updateData(Customer entity, Customer obj) {
		entity.setName(obj.getName());
		entity.setCpf(obj.getCpf());
		entity.setDocument(obj.getDocument());
	}

}
