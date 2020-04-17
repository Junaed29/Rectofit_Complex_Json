package com.example.rectofitcomplexjson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Employees {

    @Expose
    @SerializedName("data")
    private List<Data> data;


    @Expose
    @SerializedName("status")
    private String status;

    public Employees(List<Data> data, String status) {
        this.data = data;
        this.status = status;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
