package com.example.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.thymeleaf.TemplateEngine;

    @Component
    public class MailService  {

        JavaMailSender emailSender;

        public MailService(JavaMailSender emailSender) {
            this.emailSender = emailSender;
        }

        @Autowired
        TemplateEngine templateEngine;

        public void sendSimpleMessage(String to, String subject, String text) {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);
            try {
                emailSender.send(message);

            } catch (Exception e) {
                System.out.println("Invalid email...");
            }
        }
    }