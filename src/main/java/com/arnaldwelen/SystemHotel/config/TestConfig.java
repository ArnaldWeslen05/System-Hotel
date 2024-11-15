package com.arnaldwelen.SystemHotel.config;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.arnaldwelen.SystemHotel.entites.Customer;
import com.arnaldwelen.SystemHotel.entites.Reservation;
import com.arnaldwelen.SystemHotel.repository.CustomerRepository;
import com.arnaldwelen.SystemHotel.repository.ReservationRepository;

@Configuration
public class TestConfig implements CommandLineRunner {

    @Autowired
    private CustomerRepository customerRepository;
    
    @Autowired
    private ReservationRepository reservationRepository;

    @Override
    public void run(String... args) throws Exception {
        Customer customer1 = new Customer();
        customer1.setName("Arnaldo Weslen");
        customer1.setCpf(34003404);
        customer1.setDocument("123456789");
   
        customerRepository.save(customer1);  
        
        
        Reservation reservation = new Reservation();
        reservation.setCheckIn(new Date()); 
        reservation.setCheckOut(new Date()); 
        reservation.setClient(customer1);     
        
        reservationRepository.save(reservation);
    }
}
