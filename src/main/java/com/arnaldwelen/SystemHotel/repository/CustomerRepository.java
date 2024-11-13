package com.arnaldwelen.SystemHotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arnaldwelen.SystemHotel.entites.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{

}
