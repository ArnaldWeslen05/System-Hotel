package com.arnaldwelen.SystemHotel.entites;

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
@Table(name = "tb_consumption")
public class Consumption {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long consumptionId;
	
	private Integer quantity; 
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "reservation_id", nullable = false)
	private Reservation reservation;
	
	@ManyToOne
	@JoinColumn(name = "product_id", nullable = false)
	private Product product;
	
	
	public Consumption () {
		
	}


	public Consumption(Long consumptionId, Integer quantity, Reservation reservation, Product product) {
		super();
		this.consumptionId = consumptionId;
		this.quantity = quantity;
		this.reservation = reservation;
		this.product = product;
	}


	public Long getConsumptionId() {
		return consumptionId;
	}


	public void setConsumptionId(Long consumptionId) {
		this.consumptionId = consumptionId;
	}


	public Integer getQuantity() {
		return quantity;
	}


	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}


	public Reservation getReservation() {
		return reservation;
	}


	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}


	public Product getProduct() {
		return product;
	}


	public void setProduct(Product product) {
		this.product = product;
	}


	@Override
	public int hashCode() {
		return Objects.hash(consumptionId);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Consumption other = (Consumption) obj;
		return Objects.equals(consumptionId, other.consumptionId);
	}
	
	
}
