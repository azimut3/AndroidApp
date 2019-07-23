package com.example.androidapp.data;

public class Vessel {
    private String berth;
    private String vesselName;
    private String date;
    private String agent;
    private String flag;
    private String code;

    public Vessel(){

    }

    public Vessel(String berth, String vesselName, String date){
        this.berth = berth;
        this.vesselName = vesselName;
        this.date = date;
    }

    public String getBerth() {
        return berth;
    }

    public Vessel setBerth(String berth) {

        this.berth = berth;
        return this;
    }

    public String getVesselName() {
        return vesselName;
    }

    public Vessel setVesselName(String vesselName) {

        this.vesselName = vesselName;
        return this;
    }

    public String getDate() {
        return date;
    }

    public Vessel setDate(String date) {

        this.date = date;
        return this;
    }

    public String getAgent() {
        return agent;
    }

    public Vessel setAgent(String agent) {
        this.agent = agent;
        return this;
    }

    public String getFlag() {
        return flag;
    }

    public Vessel setFlag(String flag) {
        this.flag = flag;
        return this;
    }

    public String getCode() {
        return code;
    }

    public Vessel setCode(String code) {
        this.code = code;
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Vessel)) return false;
        return getVesselName().equals(((Vessel) obj).getVesselName());
    }
}
