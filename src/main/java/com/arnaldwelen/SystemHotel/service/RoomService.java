package com.arnaldwelen.SystemHotel.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arnaldwelen.SystemHotel.entites.Room;
import com.arnaldwelen.SystemHotel.repository.RoomRepository;

@Service
public class RoomService {

	@Autowired
	private RoomRepository service;
	
	public List<Room> findAll(){
		return service.findAll();
	}
	
	public Room findById(Long Id) {
		Optional<Room> obj = service.findById(Id);
		return obj.get();
	}
}
