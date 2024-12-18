package com.arnaldwelen.SystemHotel.entites;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.arnaldwelen.SystemHotel.entites.enums.ReservationStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@JsonIgnoreProperties({"room", "client", "list", "consumptions"})
@Table(name = "tb_reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Instant checkIn;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Instant checkOut;

    private Integer reservationStatus;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Customer client;

    @ManyToOne
    @JoinColumn(name = "room_id")
    @JsonManagedReference
    private Room room;

    @OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL)
    private List<Payment> list = new ArrayList<>();

    @OneToMany(mappedBy = "reservation")
    private List<Consumption> consumptions = new ArrayList<>();
    
  


    public Reservation() {
        this.reservationStatus = ReservationStatus.WAITING_PAYMENT.getCode();
    }

    public Reservation(Long id, Instant checkIn, Instant checkOut, ReservationStatus reservationStatus, Customer client,
                       Room room) {
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

    public Instant getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Instant checkIn) {
        this.checkIn = checkIn;
    }

    public Instant getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(Instant checkOut) {
        this.checkOut = checkOut;
    }

    public ReservationStatus getReservationStatus() {
        return reservationStatus != null ? ReservationStatus.valueOf(reservationStatus) : null;
    }

    public void setReservationStatus(ReservationStatus reservationStatus) {
        if (reservationStatus != null) {
            this.reservationStatus = reservationStatus.getCode();
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

    public List<Consumption> getConsumptions() {
        return consumptions;
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
