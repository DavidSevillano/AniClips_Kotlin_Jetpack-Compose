package com.example.AniClips.security.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class MailtrapMailSender {

    @Autowired
    private JavaMailSender mailSender;

    @Async
    public void sendMail(String to, String subject, String message) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom("hello@demomailtrap.co");
            mailMessage.setTo(to);
            mailMessage.setSubject(subject);
            mailMessage.setText(message);

            mailSender.send(mailMessage);
            System.out.println("Correo enviado correctamente");
        } catch (Exception e) {
            System.err.println("Error al enviar el correo: " + e.getMessage());
            e.printStackTrace();
        }
    }
}