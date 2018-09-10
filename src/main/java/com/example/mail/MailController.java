package com.example.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;

@Controller
public class MailController {

    @Autowired
    public JavaMailSender emailSender;

    @Autowired
    Repository repository;

    @Autowired
    APIData apiData;

    @PostConstruct
    public void CreateThread() {
        Reminder reminder = new Reminder(emailSender, repository, apiData);

        Thread thread = new Thread(reminder);
        thread.start();
    }
}
