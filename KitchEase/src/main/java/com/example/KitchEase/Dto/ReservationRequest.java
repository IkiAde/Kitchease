package com.example.KitchEase.Dto;

import java.time.*;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.NotNull;

public class ReservationRequest {
	@NotBlank(message = "Le nom est obligatoire")
    private String nom;

    @NotBlank(message = "L'email est obligatoire")
    @Email(message = "L'email doit être valide")
    private String email;

    @NotBlank(message = "Le téléphone est obligatoire")
    @Pattern(regexp = "^(\\+33|0)[1-9](\\d{2}){4}$", message = "Le téléphone doit être au format français (ex: +33 X XX XX XX XX ou 0X XX XX XX XX)")
    private String telephone;

    @NotNull(message = "La date est obligatoire")
    private LocalDate date;

    @NotNull(message = "L'heure est obligatoire")
    private LocalTime heure;

    @Min(value = 1, message = "Le nombre de personnes doit être au moins 1")
    @Max(value = 8, message = "Le nombre de personnes ne peut pas dépasser 8")
    private int nombrePersonnes;

    
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        // Normalisation du numéro
        this.telephone = telephone.replaceAll("\\s+", "") // Supprime les espaces
                                 .replaceAll("^0", "+33"); // Remplace le 0 initial par +33
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getHeure() {
        return heure;
    }

    public void setHeure(LocalTime heure) {
        this.heure = heure;
    }

    public int getNombrePersonnes() {
        return nombrePersonnes;
    }

    public void setNombrePersonnes(int nombrePersonnes) {
        this.nombrePersonnes = nombrePersonnes;
    }
}
