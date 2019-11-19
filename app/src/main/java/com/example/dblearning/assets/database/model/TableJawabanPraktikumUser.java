package com.example.dblearning.assets.database.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "TableJawabanPraktikum")
public class TableJawabanPraktikumUser {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int idJawabanPraktikumUser;
    private String jawabanUser;
    private int soalId;

    public TableJawabanPraktikumUser(int idJawabanPraktikumUser, String jawabanUser, int soalId) {
        this.idJawabanPraktikumUser = idJawabanPraktikumUser;
        this.jawabanUser = jawabanUser;
        this.soalId = soalId;
    }

    public int getIdJawabanPraktikumUser() {
        return idJawabanPraktikumUser;
    }

    public void setIdJawabanPraktikumUser(int idJawabanPraktikumUser) {
        this.idJawabanPraktikumUser = idJawabanPraktikumUser;
    }

    public String getJawabanUser() {
        return jawabanUser;
    }

    public void setJawabanUser(String jawabanUser) {
        this.jawabanUser = jawabanUser;
    }

    public int getSoalId() {
        return soalId;
    }

    public void setSoalId(int soalId) {
        this.soalId = soalId;
    }
}
