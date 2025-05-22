package com.example.KitchEase.service;

import java.time.*;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.KitchEase.Dto.ReservationRequest;
import com.example.KitchEase.Interface.ReservationItf;
import com.example.KitchEase.entity.*;
import com.example.KitchEase.repository.*;

import jakarta.transaction.Transactional;

@Service
public class ReservationService implements ReservationItf{
	
	@Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private TableRestaurantRepository tableRepository;
    
    @Autowired
    private ClientRepository clientRepository;
    

    @Transactional
    public Reservation createReservation(ReservationRequest request) {
        
        // Vérifier le nombre de personnes
        if (request.getNombrePersonnes() < 1 || request.getNombrePersonnes() > 8) {
            throw new IllegalArgumentException("Le nombre de personnes doit être entre 1 et 8");
        }
        
        // Normaliser le numéro de téléphone
        String telephone = normalizePhoneNumber(request.getTelephone());
        request.setTelephone(telephone);
        
        // Créer et sauvegarder le client
        Client client = new Client();
        client.setNom(request.getNom());
        client.setEmail(request.getEmail());
        client.setTelephone(request.getTelephone());
        client = clientRepository.save(client); // Sauvegarder le client

        // Trouver une table disponible
        List<TableRestaurant> tablesDisponibles = tableRepository.findByCapaciteGreaterThanEqual(request.getNombrePersonnes());

        TableRestaurant tableDisponible = tablesDisponibles.stream()
            .filter(table -> reservationRepository.findByDateAndHeure(request.getDate(), request.getHeure()).stream()
                .noneMatch(reservation -> reservation.getTable().equals(table)))
            .findFirst()
            .orElseThrow(() -> new RuntimeException("Aucune table disponible pour cette date et heure"));

        // Créer la réservation
        Reservation reservation = new Reservation();
        reservation.setDate(request.getDate());
        reservation.setHeure(request.getHeure());
        reservation.setNombrePersonnes(request.getNombrePersonnes());
        reservation.setClient(client); // Associer le client sauvegardé
        reservation.setTable(tableDisponible);
        
   
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

    private String normalizePhoneNumber(String phone) {
        if (phone == null) return null;
        
        // Supprimer tous les caractères non numériques
        String digits = phone.replaceAll("[^0-9]", "");
        
        // Si commence par 33 et a 9 chiffres après -> convertir en +33
        if (digits.startsWith("33") && digits.length() == 11) {
            return "+33 " + digits.substring(2, 3) + " " + digits.substring(3, 5) + " " 
                   + digits.substring(5, 7) + " " + digits.substring(7, 9) + " " + digits.substring(9);
        }
        // Si commence par 0 et a 9 chiffres après -> formater avec espaces
        else if (digits.startsWith("0") && digits.length() == 10) {
            return digits.substring(0, 2) + " " + digits.substring(2, 4) + " " 
                   + digits.substring(4, 6) + " " + digits.substring(6, 8) + " " + digits.substring(8);
        }
        
        // Si déjà bien formaté, retourner tel quel
        return phone;
    }
}
