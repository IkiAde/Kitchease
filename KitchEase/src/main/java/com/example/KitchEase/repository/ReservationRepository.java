package com.example.KitchEase.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.KitchEase.entity.Reservation;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface ReservationRepository extends CrudRepository<Reservation, Long> {
    List<Reservation> findByDateAndHeure(LocalDate date, LocalTime heure);
}