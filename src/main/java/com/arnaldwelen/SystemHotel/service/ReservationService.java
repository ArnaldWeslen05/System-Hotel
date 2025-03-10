package com.arnaldwelen.SystemHotel.service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arnaldwelen.SystemHotel.entites.Reservation;
import com.arnaldwelen.SystemHotel.repository.ReservationRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;
    
    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }
    
    public Reservation findById(Long id) {
        Optional<Reservation> obj = reservationRepository.findById(id);
        return obj.orElseThrow(() -> new EntityNotFoundException("Reserva não encontrada com id: " + id));
    }
    
    public Reservation insert(Reservation obj) {
    	
    	
    	if(obj.getRoom() == null || obj.getRoom().getId() == null) {
    		throw new IllegalArgumentException("Room é obrigatório e deve conter um ID.");
    	}
    	
    	if(!isRoomAvailable(obj.getRoom().getId(), obj.getCheckIn(), obj.getCheckOut())) {
    		throw new IllegalArgumentException("O quarto já está reservado para o perido selecionado");
    	}
    	
        return reservationRepository.save(obj);
        
    }
    
    public boolean isRoomAvailable (Long roomId, Instant checkIn, Instant checkOut ) {
    	List<Reservation > conflictingReservations = reservationRepository.findByRoomAndPeriod(roomId, checkIn , checkOut);
    	return conflictingReservations.isEmpty();
    }
    public void delete(Long id) {
        if (!reservationRepository.existsById(id)) {
            throw new EntityNotFoundException("Reserva não encontrada com id: " + id);
        }
        reservationRepository.deleteById(id);
    }

    public Reservation update(Long id, Reservation obj) {
        Reservation entity = reservationRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Reserva não encontrada com id: " + id));
        updateData(entity, obj);
        return reservationRepository.save(entity);
    }

    private void updateData(Reservation entity, Reservation obj) {
        entity.setCheckIn(obj.getCheckIn());
        entity.setCheckOut(obj.getCheckOut());
        entity.setRoom(obj.getRoom());
        entity.setReservationStatus(obj.getReservationStatus());
    }
}
