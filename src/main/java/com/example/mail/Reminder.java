package com.example.mail;

import org.springframework.mail.javamail.JavaMailSender;

public class Reminder implements Runnable {

    JavaMailSender javaMailSender;

    public Reminder(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public String sendMail(String mail, String origin, String time, String type) {

        MailService emailService = new MailService(javaMailSender);

        String to = mail;
        String subject = "Beräknad ankomsttid är " + time + "till " + origin;
        String text = "Hej! " + "\n" +
                "Beräknad ankomsttid för din " + type +
                "är " + time + "till hållplats " + origin + "." + "\n" + "\n" +
                "Med vänliga hälsningar" + "\n" +
                "Push SL";

        emailService.sendSimpleMessage(to, subject, text);

        return text;
    }

    @Override
    public void run() {

        while (true) {
            sendMail("asd", "asdfas", "sdf", "jkshdf");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}

