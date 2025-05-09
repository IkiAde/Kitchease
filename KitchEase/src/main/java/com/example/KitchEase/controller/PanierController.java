package com.example.KitchEase.controller;

import com.example.KitchEase.entity.Panier;
import com.example.KitchEase.entity.PanierItem;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/panier")
public class PanierController {

    @PostMapping("/ajouter")
<<<<<<< HEAD
    public void ajouterArticle(@RequestBody PanierItem item, HttpSession session) {
        if (item.getQuantite() <= 0 || item.getQuantite() > 10) {
            throw new IllegalArgumentException("La quantité doit être entre 1 et 10");
        }
        Panier panier = (Panier) session.getAttribute("panier");
        if (panier == null) {
            panier = new Panier();
            session.setAttribute("panier", panier);
        }
        panier.addItem(item);
=======
    public ResponseEntity<String> ajouterArticles(@RequestBody List<PanierItem> items, HttpSession session) {
        Panier panier = getOrCreatePanier(session);
        
        // Synchronisation complète du panier
        panier.setItems(items);
        
        return ResponseEntity.ok("Panier mis à jour avec succès");
>>>>>>> 67d51517aeeee8df3c85d940132e75ed7dc637a9
    }

    @GetMapping("/recapitulatif")
    public List<PanierItem> getPanier(HttpSession session) {
        Panier panier = (Panier) session.getAttribute("panier");
        return panier != null ? panier.getItems() : List.of();
    }

    @GetMapping("/total")
    public double getTotal(HttpSession session) {
        Panier panier = (Panier) session.getAttribute("panier");
        return panier != null ? panier.getTotalPanier() : 0;
    }

    @PostMapping("/vider")
    public void viderPanier(HttpSession session) {
        session.removeAttribute("panier");
    }

    @PostMapping("/finaliser")
    public ResponseEntity<Map<String, String>> finaliserCommande(
            @RequestBody Map<String, String> requestBody,
            HttpSession session) {
        
        String numeroCommande = "CMD-" + (int)(Math.random() * 1000000);
        
        Map<String, String> response = new HashMap<>();
        response.put("numeroCommande", numeroCommande);
        
        // Vider le panier après validation
        session.removeAttribute("panier");
        
        return ResponseEntity.ok(response);
    }

    private Panier getOrCreatePanier(HttpSession session) {
        Panier panier = (Panier) session.getAttribute("panier");
        if (panier == null) {
            panier = new Panier();
            session.setAttribute("panier", panier);
        }
        return panier;
    }
}