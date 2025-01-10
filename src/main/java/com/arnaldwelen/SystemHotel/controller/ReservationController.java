package com.arnaldwelen.SystemHotel.controller;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.arnaldwelen.SystemHotel.entites.Reservation;
import com.arnaldwelen.SystemHotel.service.ConsumptionService;
import com.arnaldwelen.SystemHotel.service.ReservationService;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping(value = "/reservations")
public class ReservationController {

    @Autowired
    private ReservationService service;

    @Autowired
    private ConsumptionService consumptionService;

    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> findAll() {
        List<Reservation> list = service.findAll();
        List<Map<String, Object>> response = list.stream().map(reservation -> {
            Map<String, Object> reservationData = new HashMap<>();
            reservationData.put("reservation", reservation);
            double totalConsumption = consumptionService.calculateTotalConsumption(reservation.getId());
            reservationData.put("totalConsumption", totalConsumption);
            return reservationData;
        }).toList();
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> findById(@PathVariable Long id) {
        Reservation obj = service.findById(id);
        double totalConsumption = consumptionService.calculateTotalConsumption(id);
        Map<String, Object> response = new HashMap<>();
        response.put("reservation", obj);
        response.put("totalConsumption", totalConsumption);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping
    public ResponseEntity<Reservation> insert(@RequestBody Reservation obj) {
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Reservation> update(@PathVariable Long id, @RequestBody Reservation obj) {
        try {
            obj = service.update(id, obj);
            return ResponseEntity.ok().body(obj);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}

