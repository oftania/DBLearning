package com.example.dblearning.assets.retrofit.response;

import com.example.dblearning.assets.model.SoalPraktikum;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponSoalPraktikum {
    @SerializedName("status")
    private boolean status;
    @SerializedName("praktikum")
    private List<SoalPraktikum> praktikumList;
    @SerializedName("message")
    private String message;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<SoalPraktikum> getPraktikumList() {
        return praktikumList;
    }

    public void setPraktikumList(List<SoalPraktikum> praktikumList) {
        this.praktikumList = praktikumList;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
