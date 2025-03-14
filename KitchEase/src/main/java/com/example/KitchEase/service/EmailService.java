package com.example.KitchEase.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.example.KitchEase.entity.Reservation;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;



@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void envoyerConfirmationReservation(String destinataire, Reservation reservation) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(destinataire);
            helper.setSubject("Confirmation de réservation - KitchEase");
            helper.setText(
                "<h3>Bonjour,</h3>" +
                "<p>Votre réservation a été confirmée :</p>" +
                "<ul>" +
                "<li>Date : " + reservation.getDate() + "</li>" +
                "<li>Heure : " + reservation.getHeure() + "</li>" +
                "<li>Nombre de personnes : " + reservation.getNombrePersonnes() + "</li>" +
                "</ul>" +
                "<p>Merci d'avoir choisi KitchEase !</p>",
                true
            );

            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
