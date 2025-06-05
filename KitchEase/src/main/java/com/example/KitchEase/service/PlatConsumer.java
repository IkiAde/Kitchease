package com.example.KitchEase.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.example.KitchEase.entity.Plat;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class PlatConsumer {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = "plats-updates", groupId = "reservation-group")
    public void consume(String message) {
        try {
            Plat plat = objectMapper.readValue(message, Plat.class);
            System.out.println("Nouveau plat reçu via Kafka : " + plat.getNom());
            // Ici tu pourrais sauvegarder dans la base de données locale par exemple
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
