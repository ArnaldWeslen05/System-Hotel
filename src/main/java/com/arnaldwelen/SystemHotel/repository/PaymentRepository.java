package com.arnaldwelen.SystemHotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arnaldwelen.SystemHotel.entites.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long>{

}
