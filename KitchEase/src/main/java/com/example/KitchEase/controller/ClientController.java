package com.example.KitchEase.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/KitchEase")
public class ClientController {

    @GetMapping("/home")
    public String pageAccueil() {
        return "home"; 
    }
    
    @PostMapping("/rechercher-disponibilites")
    public String rechercherDisponibilites(@RequestParam("date") String date, 
                                           @RequestParam("personnes") int personnes, 
                                           Model model) {
        // Ici, on vérifiera les disponibilités plus tard
        model.addAttribute("message", "Recherche en cours...");
        return "home";
    }

}
