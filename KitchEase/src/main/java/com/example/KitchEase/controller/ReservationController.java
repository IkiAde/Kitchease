package com.example.KitchEase.controller;

import java.time.*;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.example.KitchEase.Dto.ReservationRequest;
import com.example.KitchEase.entity.*;
import com.example.KitchEase.service.ReservationService;

import jakarta.validation.Valid;


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

    @GetMapping("/back-office")
    public String showBackOfficePage(Model model) {
        return "redirect:http://localhost:8081/kitcheaseGestion/home"; // back office login home.html
    }
      
    @GetMapping("/available-times")
    public String getAvailableTimes(
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestParam("nombrePersonnes") int nombrePersonnes,
            Model model) {

        // Vérifier que la date n'est pas dans le passé
        if (date.isBefore(LocalDate.now())) {
            date = LocalDate.now(); // Corriger automatiquement à la date du jour
        }

        List<LocalTime> availableTimes = reservationService.getAvailableTimes(date, nombrePersonnes);
        model.addAttribute("date", date);
        model.addAttribute("nombrePersonnes", nombrePersonnes);
        model.addAttribute("availableTimes", availableTimes);

        return "reservations/home";
    }

    // Affiche le formulaire de confirmation de réservation
    @GetMapping("/new-reservation")
    public String showReservationForm(
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestParam("heure") @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime heure,
            @RequestParam("nombrePersonnes") int nombrePersonnes,
            Model model) {

        model.addAttribute("date", date);
        model.addAttribute("heure", heure);
        model.addAttribute("nombrePersonnes", nombrePersonnes);

        return "reservations/new-reservation"; // Vue Thymeleaf
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