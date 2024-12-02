package com.arnaldwelen.SystemHotel.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arnaldwelen.SystemHotel.entites.Room;
import com.arnaldwelen.SystemHotel.repository.RoomRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class RoomService {

    @Autowired
    private RoomRepository repository;

    public List<Room> findAll() {
        return repository.findAll();
    }

    public Room findById(Long id) {
        Optional<Room> obj = repository.findById(id);
        return obj.orElseThrow(() -> new EntityNotFoundException("Quarto não encontrado com id: " + id));
    }

    public Room insert(Room obj) {
        return repository.save(obj);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Quarto não encontrado com id: " + id);
        }
        repository.deleteById(id);
    }

    public Room update(Long id, Room obj) {
        try {
            Room entity = repository.getReferenceById(id);
            updateData(entity, obj);
            return repository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("Quarto não encontrado com id: " + id);
        }
    }

    private void updateData(Room entity, Room obj) {
        entity.setPrice(obj.getPrice());
        entity.setRoomNumber(obj.getRoomNumber());
        entity.setType(obj.getType());
    }
}
