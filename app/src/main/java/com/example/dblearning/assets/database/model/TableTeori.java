package com.example.dblearning.assets.database.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity(tableName = "TableTeori")
public class TableTeori {
    @PrimaryKey()
    private int idTeori;
    private String butir_soal;

    public TableTeori(int idTeori, String butir_soal) {
        this.idTeori = idTeori;
        this.butir_soal = butir_soal;
    }

    public int getIdTeori() {
        return idTeori;
    }

    public void setIdTeori(int idTeori) {
        this.idTeori = idTeori;
    }

    public String getButir_soal() {
        return butir_soal;
    }

    public void setButir_soal(String butir_soal) {
        this.butir_soal = butir_soal;
    }
}
