package com.example.KitchEase.Interface;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.example.KitchEase.Dto.ReservationRequest;
import com.example.KitchEase.entity.*;



public interface ReservationItf {
	
	public Reservation createReservation(ReservationRequest request);
	
	public List<LocalTime> getAvailableTimes(LocalDate date, int nombrePersonnes);
	
	
}
