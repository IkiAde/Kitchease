package com.example.KitchEase.entity;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class TableRestaurant {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTable;
    private int capacite;

    public TableRestaurant(int capacite) {
		
		this.capacite = capacite;
	}
    
    public TableRestaurant() {};
    
    public int getCapacite() {
		return capacite;
	}
    
	public void setCapacite(int capacite) {
		this.capacite = capacite;
	}
	
	public Long getIdTable() {
		return idTable;
	}
}


