package com.example.KitchEase.service;

import java.time.*;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.KitchEase.Dto.ReservationRequest;
import com.example.KitchEase.Interface.ReservationItf;
import com.example.KitchEase.entity.*;
import com.example.KitchEase.repository.*;

@Service
public class ReservationService implements ReservationItf{
	
	@Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private TableRepository tableRepository;

    public Reservation createReservation(ReservationRequest request) {
        // Trouver une table disponible pour la date et l'heure choisies
        List<TableRestaurant> tablesDisponibles = tableRepository.findByCapaciteGreaterThanEqual(request.getNombrePersonnes());

        TableRestaurant tableDisponible = tablesDisponibles.stream()
            .filter(table -> reservationRepository.findByDateAndHeure(request.getDate(), request.getHeure()).stream()
                .noneMatch(reservation -> reservation.getTable().equals(table)))
            .findFirst()
            .orElseThrow(() -> new RuntimeException("Aucune table disponible pour cette date et heure"));

        // Créer un client à partir des données de la requête
        Client client = new Client();
        client.setNom(request.getNom());
        client.setEmail(request.getEmail());
        client.setTelephone(request.getTelephone());

        // Créer la réservation
        Reservation reservation = new Reservation();
        reservation.setDate(request.getDate());
        reservation.setHeure(request.getHeure());
        reservation.setNombrePersonnes(request.getNombrePersonnes());
        reservation.setClient(client);
        reservation.setTable(tableDisponible);

        // Enregistrer la réservation dans la base de données
        return reservationRepository.save(reservation);
    }

	@Override
	public List<LocalTime> getAvailableTimes(LocalDate date, int nombrePersonnes) {
        // Récupérer les tables disponibles pour le nombre de personnes
        List<TableRestaurant> tablesDisponibles = tableRepository.findByCapaciteGreaterThanEqual(nombrePersonnes);

        // Définir les horaires possibles
        List<LocalTime> allTimes = Arrays.asList(
            LocalTime.of(12, 0), LocalTime.of(13, 0), LocalTime.of(14, 0),
            LocalTime.of(19, 0), LocalTime.of(20, 0), LocalTime.of(21, 0)
        );

        // Filtrer les horaires disponibles
        List<LocalTime> availableTimes = new ArrayList<>();

        for (LocalTime time : allTimes) {
            boolean isAvailable = tablesDisponibles.stream().anyMatch(table -> {
                return reservationRepository.findByDateAndHeure(date, time).stream()
                    .noneMatch(reservation -> reservation.getTable().equals(table));
            });

            if (isAvailable) {
                availableTimes.add(time);
            }
        }

        return availableTimes;
    }
}
