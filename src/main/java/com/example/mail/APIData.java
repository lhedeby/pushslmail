package com.example.mail;

import com.example.mail.objects.RealTime;
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

    public List<RealTime> getRealTimeInfo(String siteId, String timewindow) {
        String format = "json";
        String key = System.getenv("RealTimeKey");

        String urlString = "http://api.sl.se/api2/realtimedeparturesV4." + format
                + "?key=" + key
                + "&siteid=" + siteId
                + "&timewindow=" + timewindow;

        String result = fetch(urlString);

        JsonObject jsonObject = new JsonParser().parse(result).getAsJsonObject().get("ResponseData").getAsJsonObject();
        List<RealTime> realTimeList = new ArrayList<>();

        addToList(realTimeList, jsonObject, "Metros");
        addToList(realTimeList, jsonObject, "Buses");
        addToList(realTimeList, jsonObject, "Trains");
        addToList(realTimeList, jsonObject, "Trams");
        addToList(realTimeList, jsonObject, "Ships");

        return realTimeList;
    }

    private void addToList(List<RealTime> list, JsonObject json, String type) {
        JsonArray array = json.get(type).getAsJsonArray();
        Gson gson = new Gson();
        for (int i = 0; i <array.size() ; i++) {
            list.add(gson.fromJson(array.get(i), RealTime.class));
        }
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
