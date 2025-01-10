package com.arnaldwelen.SystemHotel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arnaldwelen.SystemHotel.entites.Consumption;

public interface ConsumptionRepository extends JpaRepository<Consumption, Long>{

	public List<Consumption> findByReservationId(Long reservationId);

}
