package com.arnaldwelen.SystemHotel.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arnaldwelen.SystemHotel.entites.Reservation;
import com.arnaldwelen.SystemHotel.repository.ReservationRepository;

@Service
public class ReservationService {

	@Autowired
	private ReservationRepository reservationRepository;
	
	public List<Reservation> findAll(){
		return reservationRepository.findAll();
	}
	
	public Reservation findById(Long id) {
		Optional <Reservation> obj = reservationRepository.findById(id);
		return obj.get();
	}
}
