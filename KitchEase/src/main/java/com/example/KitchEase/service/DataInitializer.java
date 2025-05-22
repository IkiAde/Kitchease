package com.example.KitchEase.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private TableRestaurantService tableInitializationService;

    @Override
    public void run(String... args) throws Exception {
        // Crée 10 tables avec des capacités aléatoires
        //tableInitializationService.createRandomTables(10);
    }
}