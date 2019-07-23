package com.example.androidapp.data;

import android.os.AsyncTask;
import android.util.Log;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Parser {
    public static void parsePort(){

        String blogUrl = "http://www.port.odessa.ua/ua/pro-port/pozitsiya-suden/2012-11-21-09-16-27";
        try {
            //TreeMap<String, List<Vessel>> portBerths = new TreeMap<>();
            Document doc = Jsoup.connect(blogUrl).get();
            Elements vesselsAtPortTable = doc.getElementsByClass("ships");
            //Element table = vesselsAtPortTable.select("table");
            Elements rows = vesselsAtPortTable.select("tr");
            for (int r = 1; r < rows.size(); r++) {
                Elements line = rows.get(r).select("td");
                if (line.get(0).text().length() > 2) continue;
                Vessel vessel = new Vessel()
                        .setBerth(line.get(0).text())
                        .setVesselName(line.get(1).text())
                        .setDate(line.get(2).text())
                        .setAgent(line.get(3).text())
                        .setFlag(line.get(4).text())
                        .setCode(line.get(5).text());
                if (PortContent.getPortContentInstance()
                        .getPortBerthsMap().containsKey(vessel.getBerth())) {
                    PortContent.getPortContentInstance()
                            .getPortBerthsMap().get(vessel.getBerth())
                            .addVessel(vessel);
                    Log.d("Vessel added", vessel.toString());

                } else {
                    PortContent.getPortContentInstance().getPortBerthsMap().put(vessel.getBerth(),
                            new Berth(vessel.getBerth(), vessel));
                    Log.d("Berth added", vessel.toString());
                }

            }
            Log.d("Vessels map", PortContent.getPortContentInstance().getPortBerthsMap().toString());
        }
            catch (HttpStatusException ex) {
                ex.printStackTrace();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
    }




    }

