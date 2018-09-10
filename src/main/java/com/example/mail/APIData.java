package com.example.mail;

import com.example.mail.objects.RealTimeBusesAndMetros;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class APIData {

    public List<RealTimeBusesAndMetros> getRealTimeInfo(String siteId, String timewindow) {
        String format = "json";
        String key = System.getenv("RealTimeKey");

        String urlString = "http://api.sl.se/api2/realtimedeparturesV4." + format
                + "?key=" + key
                + "&siteid=" + siteId
                + "&timewindow=" + timewindow;

        String result = fetch(urlString);

        JsonObject jsonObject = new JsonParser().parse(result).getAsJsonObject();
        JsonArray metroArray = jsonObject.get("ResponseData").getAsJsonObject().get("Metros").getAsJsonArray();
        List<RealTimeBusesAndMetros> realTimeList = new ArrayList<>();

        Gson gson = new Gson();
        for (int i = 0; i < metroArray.size(); i++) {
            realTimeList.add(gson.fromJson(metroArray.get(i), RealTimeBusesAndMetros.class));
        }

        JsonArray busArray = jsonObject.get("ResponseData").getAsJsonObject().get("Buses").getAsJsonArray();
        for (int i = 0; i < busArray.size(); i++) {
            realTimeList.add(gson.fromJson(busArray.get(i), RealTimeBusesAndMetros.class));
        }
        return realTimeList;
    }

    private String fetch(String urlString) {
        String result = "";
        try {
            URL url = new URL(urlString);
            Scanner sc = new Scanner(url.openStream());
            result = sc.nextLine();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
