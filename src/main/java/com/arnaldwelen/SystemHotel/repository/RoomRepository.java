package com.arnaldwelen.SystemHotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arnaldwelen.SystemHotel.entites.Room;

public interface RoomRepository extends JpaRepository<Room, Long>{
	
	  boolean existsByRoomNumber(Integer roomNumber);
}
