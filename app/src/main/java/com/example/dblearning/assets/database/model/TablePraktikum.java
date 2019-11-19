package com.example.dblearning.assets.database.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity(tableName = "TablePraktikum")
public class TablePraktikum {
    @NonNull
    @PrimaryKey()
    private int idPraktikum;
    private String butir_soal;
    private String jawaban;

    public TablePraktikum(int idPraktikum, String butir_soal, String jawaban) {
        this.idPraktikum = idPraktikum;
        this.butir_soal = butir_soal;
        this.jawaban = jawaban;
    }

    public int getIdPraktikum() {
        return idPraktikum;
    }

    public void setIdPraktikum(int idPraktikum) {
        this.idPraktikum = idPraktikum;
    }

    public String getButir_soal() {
        return butir_soal;
    }

    public void setButir_soal(String butir_soal) {
        this.butir_soal = butir_soal;
    }

    public String getJawaban() {
        return jawaban;
    }

    public void setJawaban(String jawaban) {
        this.jawaban = jawaban;
    }
}
