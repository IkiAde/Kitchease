// models/PanierItem.java
package com.example.KitchEase.entity;

public class PanierItem {
	
    private String nom;
    private double prixUnitaire;
    private int quantite;

    public PanierItem() {}

    public PanierItem(String nom, double prixUnitaire, int quantite) {
        this.nom = nom;
        this.prixUnitaire = prixUnitaire;
        this.quantite = quantite;
    }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public double getPrixUnitaire() { return prixUnitaire; }
    public void setPrixUnitaire(double prixUnitaire) { this.prixUnitaire = prixUnitaire; }

    public int getQuantite() { return quantite; }
    public void setQuantite(int quantite) { this.quantite = quantite; }

    public double getTotal() {
        return prixUnitaire * quantite;
    }
}
