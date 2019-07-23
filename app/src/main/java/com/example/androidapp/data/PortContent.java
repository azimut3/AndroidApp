package com.example.androidapp.data;

import android.os.Build;

import java.util.*;

public class PortContent {
    private static PortContent portContentInstance;
    private volatile TreeMap<String, Berth> portBerths = new TreeMap<>();
    private volatile List<Berth> portBerthsList = new ArrayList<>();

    private PortContent() {
    }

    public TreeMap<String, Berth> getPortBerthsMap() {
        return portBerths;
    }

    public static PortContent getPortContentInstance() {
        if (portContentInstance == null) portContentInstance = new PortContent();
        return portContentInstance;
    }

    public List<Berth> getListOfBerths() {
        if (portBerthsList != null) portBerthsList.clear();
        if (portBerths.size() > 0) {
            portBerthsList.addAll(new ArrayList<Berth>(portBerths.values()));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                portBerthsList.sort((o1, o2) -> {
                    if (o1.getBerth().length() == o2.getBerth().length())
                        return o1.getBerth().compareTo(o2.getBerth());
                    if (o1.getBerth().length() > o2.getBerth().length()) return 1;
                    if (o1.getBerth().length() < o2.getBerth().length()) return -1;
                    if (o1.getBerth().length() == 1 && o2.getBerth().length() == 2) return -1;
                    if (o1.getBerth().length() == 2 && o2.getBerth().length() == 1) return 1;
                    return o1.getBerth().compareTo(o2.getBerth());
                    //System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                });
            } else
                portBerthsList.add(new Berth("Loading...",
                        new Vessel().setVesselName("loading...")));
        }
            return portBerthsList;
        }

}
