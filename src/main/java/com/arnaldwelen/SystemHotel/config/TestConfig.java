package com.arnaldwelen.SystemHotel.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.arnaldwelen.SystemHotel.entites.Customer;
import com.arnaldwelen.SystemHotel.repository.CustomerRepository;

@Configuration
public class TestConfig implements CommandLineRunner {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public void run(String... args) throws Exception {
        Customer customer = new Customer();
        customer.setName("Arnaldo Weslen");
        customer.setCpf(34003404);
        customer.setDocument("123456789");
        
        customerRepository.save(customer);  
    }
}
