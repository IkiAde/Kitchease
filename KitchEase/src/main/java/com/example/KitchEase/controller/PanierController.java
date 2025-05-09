package com.example.KitchEase.controller;


import org.springframework.web.bind.annotation.*;

import com.example.KitchEase.entity.Panier;
import com.example.KitchEase.entity.PanierItem;

import jakarta.servlet.http.HttpSession;

import java.util.List;

@RestController
@RequestMapping("/api/panier")
public class PanierController {

    @PostMapping("/ajouter")
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
    }

    @GetMapping("/recapitulatif")
    public List<PanierItem> getPanier(HttpSession session) {
        Panier panier = (Panier) session.getAttribute("panier");
        if (panier == null) return List.of();
        return panier.getItems();
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
}
