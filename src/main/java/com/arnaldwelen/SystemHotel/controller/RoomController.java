package com.arnaldwelen.SystemHotel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arnaldwelen.SystemHotel.entites.Room;
import com.arnaldwelen.SystemHotel.service.RoomService;

@RestController
@RequestMapping(value = "room")
public class RoomController {
	
	@Autowired
	private RoomService room;
	
	@GetMapping
	public ResponseEntity<List<Room>> findAll(){
		List<Room> list = room.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Room > findById(@PathVariable Long id){
		Room obj = room.findById(id);
		return ResponseEntity.ok().body(obj);
	}

}
