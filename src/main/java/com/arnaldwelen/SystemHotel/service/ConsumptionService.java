package com.arnaldwelen.SystemHotel.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arnaldwelen.SystemHotel.entites.Consumption;
import com.arnaldwelen.SystemHotel.entites.Reservation;
import com.arnaldwelen.SystemHotel.repository.ConsumptionRepository;
import com.arnaldwelen.SystemHotel.repository.ReservationRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ConsumptionService {

    @Autowired
    private ConsumptionRepository consumptionRepository;

    @Autowired
    private ReservationRepository reservationRepository;
    


    public List<Consumption> findAll() {
        return consumptionRepository.findAll();
    }

    public Consumption findById(Long id) {
        return consumptionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Consumption not found with id: " + id));
    }

    public Consumption insert(Consumption obj) {
        Reservation reservation = obj.getReservation();

        if (reservation != null) {
            Optional<Reservation> existingReservation = reservationRepository.findById(reservation.getId());

            if (existingReservation.isPresent()) {
                obj.setReservation(existingReservation.get());
            } else {

                Reservation tempReservation = new Reservation();
                reservationRepository.save(tempReservation);  
                obj.setReservation(tempReservation);  
            }
        } else {

            Reservation tempReservation = new Reservation();
            reservationRepository.save(tempReservation);  
            obj.setReservation(tempReservation);  
        }

        return consumptionRepository.save(obj);
    }





    public void delete(Long id) {
        if (!consumptionRepository.existsById(id)) {
            throw new EntityNotFoundException("Consumption not found with id: " + id);
        }
        consumptionRepository.deleteById(id);
    }

    public Consumption update(Long id, Consumption obj) {
        try {
            Consumption entity = consumptionRepository.getReferenceById(id);
            updateData(entity, obj);
            return consumptionRepository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("Consumption not found with id: " + id);
        }
    }

    private void updateData(Consumption entity, Consumption obj) {
        entity.setQuantity(obj.getQuantity());
        entity.setProduct(obj.getProduct());

        if (obj.getReservation() != null && obj.getReservation().getId() != null) {
            Reservation reservation = reservationRepository.findById(obj.getReservation().getId())
                    .orElseThrow(() -> new EntityNotFoundException(
                            "Reservation not found with id: " + obj.getReservation().getId()));
            entity.setReservation(reservation);
        }
    }
}
