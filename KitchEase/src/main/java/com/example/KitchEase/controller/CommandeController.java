package com.example.KitchEase.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CommandeController {

    @GetMapping("/commande/validation")
    public String showValidationPage() {
        return "reservations/validation";
    }
}