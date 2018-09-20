package com.example.mail;

import com.example.mail.objects.DBdata;
import com.example.mail.objects.RealTime;
import org.springframework.mail.javamail.JavaMailSender;

import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

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

        if(type.equals("METRO")) {
            type = "tunnelbana";
        } else if(type.equals("BUS"))  {
            type = "buss";
        }

        String to = mail;
        String subject = "Din " + type + " förväntas anlända om " + time + " minuter";
        String text = "Hej! " + "\n" +
                "Din " + type + " förväntas anlända om " + time + " minuter" +
                " till hållplats " + origin + "." + "\n" + "\n" +
                "Med vänliga hälsningar" + "\n" +
                "Push SL";

        emailService.sendSimpleMessage(to, subject, text);

        return text;
    }

    @Override
    public void run() {

        while (true) {
            try {
                List<DBdata> dbList = repository.listDBdata();
                for(DBdata data : dbList) {
                    List<RealTime> realTimeList = apiData.getRealTimeInfo(data.siteInfo, "60");
                    for(RealTime rt : realTimeList) {
                        if(rt.JourneyNumber.equals(data.journeynumber)) {

                            rt.ExpectedDateTime = rt.ExpectedDateTime.replace("T", " ");
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                            LocalDateTime realTime = LocalDateTime.parse(rt.ExpectedDateTime, formatter);
                            LocalDateTime currentTime = LocalDateTime.now();
                            Duration timeLeft = Duration.between(currentTime, realTime);

                            if(timeLeft.getSeconds() / 60 < Integer.parseInt(data.timebeforeleavning)) {
                                sendMail(data.mail, rt.StopAreaName, data.timebeforeleavning, rt.TransportMode);
                                repository.deleteData(data.journeynumber);
                            }
                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(15000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

