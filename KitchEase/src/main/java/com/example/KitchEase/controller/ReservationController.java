package com.example.KitchEase.controller;

import java.time.*;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.example.KitchEase.Dto.ReservationRequest;
import com.example.KitchEase.entity.*;
import com.example.KitchEase.service.ReservationService;

import jakarta.validation.Valid;


/*@Controller
@RequestMapping("/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    // Afficher la page d'accueil
    @GetMapping("/")
    public String showHomePage(Model model) {
        return "index"; // Renvoie le template index.html
    }

    // Afficher les horaires disponibles
    @GetMapping("/available-times")
    public String getAvailableTimes(
            @RequestParam LocalDate date,
            @RequestParam int nombrePersonnes,
            Model model) {

        List<LocalTime> availableTimes = reservationService.getAvailableTimes(date, nombrePersonnes);
        model.addAttribute("date", date);
        model.addAttribute("nombrePersonnes", nombrePersonnes);
        model.addAttribute("availableTimes", availableTimes);

        return "available-times"; // Renvoie le template available-times.html
    }

    // Afficher le formulaire de réservation
    @GetMapping("/new-reservation")
    public String showReservationForm(
            @RequestParam LocalDate date,
            @RequestParam LocalTime heure,
            @RequestParam int nombrePersonnes,
            Model model) {

        model.addAttribute("date", date);
        model.addAttribute("heure", heure);
        model.addAttribute("nombrePersonnes", nombrePersonnes);

        return "new-reservation"; // Renvoie le template new-reservation.html
    }

    // Confirmer la réservation
    @PostMapping
    public String createReservation(@ModelAttribute ReservationRequest request, Model model) {
        Client client = new Client();
        client.setNom(request.getNom());
        client.setEmail(request.getEmail());
        client.setTelephone(request.getTelephone());

        Reservation reservation = reservationService.createReservation(
            client, request.getDate(), request.getHeure(), request.getNombrePersonnes()
        );

        model.addAttribute("reservation", reservation);
        return "reservation-confirmation"; // Renvoie le template reservation-confirmation.html
    }
}*/

@Controller
@RequestMapping("/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    // Affiche la page d'accueil
    @GetMapping("/")
    public String showHomePage(Model model) {
        return "reservations/home"; // Chemin complet vers home.html
    }

    // Affiche les horaires disponibles
    @GetMapping("/available-times")
    public String getAvailableTimes(
            @RequestParam LocalDate date,
            @RequestParam int nombrePersonnes,
            Model model) {

        // Récupérer les horaires disponibles
        List<LocalTime> availableTimes = reservationService.getAvailableTimes(date, nombrePersonnes);

        // Ajouter les données au modèle
        model.addAttribute("date", date);
        model.addAttribute("nombrePersonnes", nombrePersonnes);
        model.addAttribute("availableTimes", availableTimes);

        return "reservations/home"; // Retourne la vue home.html dans le dossier reservations
    }

    // Affiche le formulaire de confirmation de réservation
    @GetMapping("/new-reservation")
    public String showReservationForm(
            @RequestParam LocalDate date,
            @RequestParam LocalTime heure,
            @RequestParam int nombrePersonnes,
            Model model) {

        model.addAttribute("date", date);
        model.addAttribute("heure", heure);
        model.addAttribute("nombrePersonnes", nombrePersonnes);

        return "reservations/new-reservation"; // Chemin complet vers new-reservation.html
    }

    // Traite la confirmation de réservation
    @PostMapping
    public String createReservation(@Valid @ModelAttribute ReservationRequest request, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "new-reservation"; // Retourne vers le formulaire en cas d'erreur
        }

        // Créer la réservation
        Reservation reservation = reservationService.createReservation(request);

        // Ajouter la réservation au modèle pour l'affichage
        model.addAttribute("reservation", reservation);

        return "reservations/confirmation"; // Retourne la vue reservation-confirmation.html
    }
}