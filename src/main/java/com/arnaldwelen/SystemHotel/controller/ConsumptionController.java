package com.arnaldwelen.SystemHotel.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.arnaldwelen.SystemHotel.entites.Consumption;
import com.arnaldwelen.SystemHotel.service.ConsumptionService;

@RestController
@RequestMapping(value = "consumption")
public class ConsumptionController {

	@Autowired
	private ConsumptionService service;

	@GetMapping
	public ResponseEntity<List<Consumption>> findAll() {

		List<Consumption> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Consumption> findById(@PathVariable Long id) {
		Consumption obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	@PostMapping
	public ResponseEntity<Consumption> insert(@RequestBody Consumption obj) {
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<Consumption> update(@PathVariable Long id, @RequestBody Consumption obj) {
		obj = service.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}

}
