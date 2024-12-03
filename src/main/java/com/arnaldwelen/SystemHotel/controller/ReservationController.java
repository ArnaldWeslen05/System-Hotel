package com.arnaldwelen.SystemHotel.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.arnaldwelen.SystemHotel.entites.Reservation;
import com.arnaldwelen.SystemHotel.service.ReservationService;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping(value = "reservation")
public class ReservationController {

	@Autowired
	private ReservationService service;

	@GetMapping
	public ResponseEntity<List<Reservation>> findAll() {
		List<Reservation> list = service.findAll();
		return ResponseEntity.ok().body(list);

	}

	@GetMapping("/{id}")
	public ResponseEntity<Reservation> findById(@PathVariable Long id) {
		Reservation obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	public ResponseEntity<Reservation> insert( @RequestBody Reservation obj){
		obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Reservation> update(@PathVariable Long id, @RequestBody Reservation obj) {
	    try {
	        obj = service.update(id, obj);
	        return ResponseEntity.ok().body(obj);
	    } catch (EntityNotFoundException e) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	    }
	}
	
	@ControllerAdvice
	public class GlobalExceptionHandler {

	    @ExceptionHandler(EntityNotFoundException.class)
	    public ResponseEntity<String> handleEntityNotFound(EntityNotFoundException e) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	    }
	}


}