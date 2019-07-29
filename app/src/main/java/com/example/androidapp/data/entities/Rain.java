
package com.example.androidapp.data.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Rain {

    @SerializedName("3h")
    @Expose
    private Double volume;

    public Double get3h() {
        return volume;
    }

    public void set3h(Double _3h) {
        this.volume = _3h;
    }

    @Override
    public String toString() {
        return "Rain{" +
                "volume=" + volume +
                '}';
    }
}
