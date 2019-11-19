package com.example.dblearning.assets.model;

import com.google.gson.annotations.SerializedName;

public class SoalPraktikum {
    @SerializedName("id_bank_soal_praktikum")
    private String idBankSoalPraktikum;
    @SerializedName("butir_soal")
    private String butirSoal;
    @SerializedName("jawaban")
    private String jawaban;

    public String getIdBankSoalPraktikum() {
        return idBankSoalPraktikum;
    }

    public void setIdBankSoalPraktikum(String idBankSoalPraktikum) {
        this.idBankSoalPraktikum = idBankSoalPraktikum;
    }

    public String getButirSoal() {
        return butirSoal;
    }

    public void setButirSoal(String butirSoal) {
        this.butirSoal = butirSoal;
    }

    public String getJawaban() {
        return jawaban;
    }

    public void setJawaban(String jawaban) {
        this.jawaban = jawaban;
    }
}
