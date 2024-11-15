package com.arnaldwelen.SystemHotel.entites.enums;

public enum ReservationStatus {


	WAITING_PAYMENT(1), PAID(2), CANCELED(3);

	private int code;

	private ReservationStatus(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public static ReservationStatus valueOf(int code) {
		for (ReservationStatus value : ReservationStatus.values()) {
			if (value.getCode() == code) {
				return value;
			}

		}
		throw new IllegalArgumentException("Invalid OrderStatusCode");
	}
}
