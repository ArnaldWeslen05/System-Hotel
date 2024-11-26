package com.arnaldwelen.SystemHotel.config;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.arnaldwelen.SystemHotel.entites.Customer;
import com.arnaldwelen.SystemHotel.entites.Payment;
import com.arnaldwelen.SystemHotel.entites.Reservation;
import com.arnaldwelen.SystemHotel.entites.Room;
import com.arnaldwelen.SystemHotel.entites.enums.ReservationStatus;
import com.arnaldwelen.SystemHotel.repository.CustomerRepository;
import com.arnaldwelen.SystemHotel.repository.PaymentRepository;
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
    
    @Autowired
    private PaymentRepository paymentRepository;

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
        roomRepository.save(room25);
 
        Reservation reservation1 = new Reservation();
        reservation1.setCheckIn(new Date());
        reservation1.setCheckOut(new Date());
        reservation1.setClient(customer1);
        reservation1.setRoom(room25); 
        reservation1.setReservationStatus(ReservationStatus.PAID);
        reservationRepository.save(reservation1); 

       
        Payment payment1 = new Payment();
        payment1.setDate(new Date());
        payment1.setPaymentMethod("Cartão");
        payment1.setReservation(reservation1);
        paymentRepository.save(payment1); 

        room25.getReservation().add(reservation1);
        roomRepository.save(room25); 
    }
}
