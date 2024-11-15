package com.arnaldwelen.SystemHotel.entites;

import java.util.Date;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_reservation")
public class Reservation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Date checkIn;
	private Date checkOut;
	
	@ManyToOne
	@JoinColumn(name = "client_id")
	private Customer client;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "room_id") 
	private Room room;

	public Reservation() {

	}

	public Reservation(Long id, Date checkIn, Date checkOut, Customer client, Room room) {
		super();
		this.id = id;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		this.client = client;
		this.room = room;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCheckIn() {
		return checkIn;
	}

	public void setCheckIn(Date checkIn) {
		this.checkIn = checkIn;
	}

	public Date getCheckOut() {
		return checkOut;
	}

	public void setCheckOut(Date checkOut) {
		this.checkOut = checkOut;
	}
	

	public Customer getClient() {
		return client;
	}

	public void setClient(Customer client) {
		this.client = client;
	}
	

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reservation other = (Reservation) obj;
		return Objects.equals(id, other.id);
	}
	
	

}
