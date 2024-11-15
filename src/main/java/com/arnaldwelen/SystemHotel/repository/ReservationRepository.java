package com.arnaldwelen.SystemHotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arnaldwelen.SystemHotel.entites.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation , Long>{

}
