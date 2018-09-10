package com.example.mail;

import com.example.mail.objects.DBdata;
import org.springframework.mail.javamail.JavaMailSender;

import java.sql.SQLException;

public class Reminder implements Runnable {

    JavaMailSender javaMailSender;
    Repository repository;
    APIData apiData;

    public Reminder(JavaMailSender javaMailSender, Repository repository, APIData apiData) {
        this.javaMailSender = javaMailSender;
        this.repository = repository;
        this.apiData = apiData;
    }

    public String sendMail(String mail, String origin, String time, String type) {

        MailService emailService = new MailService(javaMailSender);

        String to = mail;
        String subject = "Beräknad ankomsttid är " + time + " till " + origin;
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
            try {
                var dbList = repository.listDBdata();
                for(DBdata data : dbList) {
                    System.out.println(data);

                }

            } catch (SQLException e) {
                e.printStackTrace();
            }


//            sendMail("ludwig@hedeby.me", "asdfas", "sdf", "jkshdf");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}

