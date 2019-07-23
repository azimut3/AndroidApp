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
                portBerthsList.add(new Berth("Loading...", new Vessel().setVesselName("loading...")));
        }
            return portBerthsList;
        }


    /*public String getPortInfo(){
        TreeMap<String, List<Vessel>> vessels = getBerthMap(portBerths);
        StringBuilder builder = new StringBuilder();
        builder.append("<b>Список причалов и судов(дата швартовки):</b>").append(System.lineSeparator())
                .append(System.lineSeparator());
        if (vessels.size()>=1) {
            Set<String> set = vessels.keySet();
            for (String key : set) {
                builder.append(getBerthByNumber(key));
            }
        } else builder.append("К сожалению в данный момент нет доступа к данным с сайта одесского" +
                " порта(");
        return builder.toString();
    }

    public String getBerthByNumber(String key) {
        StringBuilder builder = new StringBuilder();
        for (Vessel vessel : portBerths) {
            if (vessel.getBerth().equals(key)) {
                builder.append(formatString(key, 2)).append(" | ").append(vessel.getVesselName())
                        .append(" <i>(").append(vessel.getDate()).append(")</i>")
                        .append(System.lineSeparator());
            } else builder.append("");
        }

        return builder.toString();
    }

    private TreeMap<String, List<Vessel>> getBerthMap(List<Vessel> list){
        TreeMap<String, List<Vessel>> map = new TreeMap<>(
                (o1, o2) -> {
            if (o1.length()==o2.length()) return o1.compareTo(o2);
            if (o1.length()>o2.length()) return 1;
            if (o1.length()<o2.length()) return -1;
            if (o1.length()==1&&o2.length()==2) return -1;
            if (o1.length()==2&&o2.length()==1) return 1;
            return o1.compareTo(o2);
        });
        for(Vessel vessel : list){
            if(map.containsKey(vessel.getBerth())){
                map.get(vessel.getBerth()).add(vessel);
            } else{
                List<Vessel> vessels = new ArrayList<>();
                vessels.add(vessel);
                map.put(vessel.getBerth(), vessels);
            }
        }
        return map;
    }

    private String formatString(String s, int width){
        return String.format("%1$" + width + "s", s).replaceAll(" ", "  ");

    }*/
}
