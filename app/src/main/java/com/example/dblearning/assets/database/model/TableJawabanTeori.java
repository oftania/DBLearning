package com.example.dblearning.assets.database.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "TableJawabanTeori")
public class TableJawabanTeori {
    @NonNull
    @PrimaryKey()
    private int idJawabanTeori;
    private String jawaban;
    private int bankSoalId;
    private boolean statusJawaban;

    public TableJawabanTeori(int idJawabanTeori, String jawaban, int bankSoalId, boolean statusJawaban) {
        this.idJawabanTeori = idJawabanTeori;
        this.jawaban = jawaban;
        this.bankSoalId = bankSoalId;
        this.statusJawaban = statusJawaban;
    }

    public int getIdJawabanTeori() {
        return idJawabanTeori;
    }

    public void setIdJawabanTeori(int idJawabanTeori) {
        this.idJawabanTeori = idJawabanTeori;
    }

    public String getJawaban() {
        return jawaban;
    }

    public void setJawaban(String jawaban) {
        this.jawaban = jawaban;
    }

    public int getBankSoalId() {
        return bankSoalId;
    }

    public void setBankSoalId(int bankSoalId) {
        this.bankSoalId = bankSoalId;
    }

    public boolean isStatusJawaban() {
        return statusJawaban;
    }

    public void setStatusJawaban(boolean statusJawaban) {
        this.statusJawaban = statusJawaban;
    }
}
