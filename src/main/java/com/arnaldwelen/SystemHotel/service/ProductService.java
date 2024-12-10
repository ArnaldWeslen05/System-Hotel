package com.arnaldwelen.SystemHotel.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arnaldwelen.SystemHotel.entites.Product;
import com.arnaldwelen.SystemHotel.repository.ProductRepository;

import jakarta.persistence.EntityNotFoundException;

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
	public Product insert(Product obj) {
        return product.save(obj);
    }
	public void delete(Long id) {
        if (!product.existsById(id)) {
            throw new EntityNotFoundException("Quarto não encontrado com id: " + id);
        }
        product.deleteById(id);
    }

    public Product update(Long id, Product obj) {
        try {
            Product entity = product.getReferenceById(id);
            updateData(entity, obj);
            return product.save(entity);
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("Quarto não encontrado com id: " + id);
        }
    }
    
    private void updateData(Product entity, Product obj) {
    	entity.setName(obj.getName());
    	entity.setPrice(obj.getPrice());
    	entity.setDescription(obj.getDescription());
    	entity.setConsumptions(obj.getConsumptions());
    }

}
