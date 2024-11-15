package com.arnaldwelen.SystemHotel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arnaldwelen.SystemHotel.entites.Reservation;
import com.arnaldwelen.SystemHotel.service.ReservationService;

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
}