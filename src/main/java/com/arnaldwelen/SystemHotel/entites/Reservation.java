package com.arnaldwelen.SystemHotel.entites;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import com.arnaldwelen.SystemHotel.entites.enums.ReservationStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_reservation")
public class Reservation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Date checkIn;
	private Date checkOut;
	private Integer reservationStatus;

	@ManyToOne
	@JoinColumn(name = "client_id")
	private Customer client;

	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "room_id")
	private Room room;
	
	@OneToMany(mappedBy = "reservation")
	private List<Payment> list = new ArrayList<>();

	public Reservation() {
		this.reservationStatus = ReservationStatus.WAITING_PAYMENT.getCode();
	}

	public Reservation(Long id, Date checkIn, Date checkOut, ReservationStatus reservationStatus, Customer client,
			Room room) {
		super();
		this.id = id;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		this.client = client;
		this.room = room;
		this.setReservationStatus(reservationStatus != null ? reservationStatus : ReservationStatus.WAITING_PAYMENT);
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

	public ReservationStatus getReservationStatus() {
		return reservationStatus != null ? ReservationStatus.valueOf(reservationStatus) : null;
	}

	public void setReservationStatus(ReservationStatus orderStatus) {
		if (orderStatus != null) {
			this.reservationStatus = orderStatus.getCode();
		}
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
