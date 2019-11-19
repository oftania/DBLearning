package com.example.dblearning.assets.retrofit.response;

import com.example.dblearning.assets.model.SoalTeori;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponSoalTeori {
    @SerializedName("status")
    private boolean status;
    @SerializedName("teori")
    private List<SoalTeori> teoriList;
    @SerializedName("message")
    private String message;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<SoalTeori> getTeoriList() {
        return teoriList;
    }

    public void setTeoriList(List<SoalTeori> teoriList) {
        this.teoriList = teoriList;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
