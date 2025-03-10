package com.example.KitchEase.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Reservation {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReservation;
    
    private LocalDate date;
    private LocalTime heure;
    private int nombrePersonnes;

    public Reservation(LocalDate date, LocalTime heure, int nombrePersonnes, Client client, TableRestaurant table) {
		
		this.date = date;
		this.heure = heure;
		this.nombrePersonnes = nombrePersonnes;
		this.client = client;
		this.table = table;
	}
    
    public Reservation() {};

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

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public TableRestaurant getTable() {
		return table;
	}

	public void setTable(TableRestaurant table) {
		this.table = table;
	}

	public Long getIdReservation() {
		return idReservation;
	}

	@ManyToOne
    private Client client;

    @ManyToOne
    private TableRestaurant table;

}


