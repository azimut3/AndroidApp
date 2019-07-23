package com.example.androidapp.data;

import java.util.ArrayList;
import java.util.List;

public class Berth {
    private String berth;
    private List<Vessel> vessels;

    public Berth(String berth, Vessel vessel) {
        this.berth = berth;
        vessels = new ArrayList<>();
        vessels.add(vessel);
    }

    public String getBerth() {
        return berth;
    }

    public void setBerth(String berth) {
        this.berth = berth;
    }

    public List<Vessel> getVessels() {
        return vessels;
    }

    public void addVessel(Vessel vessel) {
        this.vessels.add(vessel);
    }

    public String getBerthName() {
        return String.format("Berth #%s", berth);
    }

    public String getVesselsNames() {
        if (vessels != null && vessels.size()>0){
            StringBuilder builder = new StringBuilder();
            builder.append("Vessels: ");
            for (Vessel vessel : vessels){
                builder.append(vessel.getVesselName()).append(", ");
            }
            String output = builder.toString();
            return  output.substring(0, output.length()-2);
        }
        return "No info";
    }

    @Override
    public String toString() {
        return "Berth{" +
                "berth='" + berth + '\'' +
                ", vessels=" + vessels +
                '}';
    }
}
