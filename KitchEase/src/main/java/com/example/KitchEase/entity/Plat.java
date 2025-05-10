package com.example.KitchEase.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Plat {
	
	    public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public Long getIdPlat() {
		return idPlat;
	}

		private String nom;
	    private Double prix;
	    private String description;
	    private int quantite;
	    private String imageUrl; 

	    public Plat(String nom, Double prix, String description, int quantite, String imageUrl) {
	        this.nom = nom;
	        this.prix = prix;
	        this.description = description;
	        this.quantite= quantite;
	        this.imageUrl = imageUrl;
	    }
	    
	    public Plat(){};
	    
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long idPlat;

	    
	    public String getNom() {
	        return nom;
	    }

	    public void setNom(String nom) {
	        this.nom = nom;
	    }

	    public Double getPrix() {
	        return prix;
	    }

	    public void setPrix(Double prix) {
	        this.prix = prix;
	    }

	    public String getDescription() {
	        return description;
	    }

	    public void setDescription(String description) {
	        this.description = description;
	    }

	    public String getImageUrl() {
	        return imageUrl;
	    }

	    public void setImageUrl(String imageUrl) {
	        this.imageUrl = imageUrl;
	    }
}

