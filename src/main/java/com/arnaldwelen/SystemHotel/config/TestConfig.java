package com.arnaldwelen.SystemHotel.config;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.arnaldwelen.SystemHotel.entites.Customer;
import com.arnaldwelen.SystemHotel.entites.Reservation;
import com.arnaldwelen.SystemHotel.entites.Room;
import com.arnaldwelen.SystemHotel.repository.CustomerRepository;
import com.arnaldwelen.SystemHotel.repository.ReservationRepository;
import com.arnaldwelen.SystemHotel.repository.RoomRepository;

@Configuration
public class TestConfig implements CommandLineRunner {

    @Autowired
    private CustomerRepository customerRepository;
    
    @Autowired
    private ReservationRepository reservationRepository;
    
    @Autowired
    private RoomRepository roomRepository;

    @Override
    public void run(String... args) throws Exception {
        Customer customer1 = new Customer();
        customer1.setName("Arnaldo Weslen");
        customer1.setCpf(34003404);
        customer1.setDocument("123456789");
   
        customerRepository.save(customer1);  
        
        Room room25 = new Room();
        room25.setRoomNumber(25);
        room25.setPrice(150.00);
        room25.setType("Diaria");
      
        
        Reservation reservation1 = new Reservation();
        reservation1.setCheckIn(new Date()); 
        reservation1.setCheckOut(new Date()); 
        reservation1.setClient(customer1);
        reservation1.setRoom(room25);
    
        roomRepository.save(room25);
        room25.getReservation().add(reservation1);
        reservationRepository.save(reservation1);
        
    }
}
