
package com.example.androidapp.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Snow {

    @SerializedName("3h")
    @Expose
    private Double voulume;

    public Double get3h() {
        return voulume;
    }

    public void set3h(Double _3h) {
        this.voulume = _3h;
    }

}
