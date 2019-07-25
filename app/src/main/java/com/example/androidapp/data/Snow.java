
package com.example.androidapp.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Snow {

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
        return "Snow{" +
                "volume=" + volume +
                '}';
    }
}
