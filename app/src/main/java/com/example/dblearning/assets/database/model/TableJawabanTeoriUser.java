package com.example.dblearning.assets.database.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "TableJawabanTeoriUser")
public class TableJawabanTeoriUser {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int idJawabanTeoriUser;
    private int soalId;
    private int jawabanUser;

    public TableJawabanTeoriUser(int soalId, int jawabanUser) {
        this.soalId = soalId;
        this.jawabanUser = jawabanUser;
    }

    public int getIdJawabanTeoriUser() {
        return idJawabanTeoriUser;
    }

    public void setIdJawabanTeoriUser(int idJawabanTeoriUser) {
        this.idJawabanTeoriUser = idJawabanTeoriUser;
    }

    public int getSoalId() {
        return soalId;
    }

    public void setSoalId(int soalId) {
        this.soalId = soalId;
    }

    public int getJawabanUser() {
        return jawabanUser;
    }

    public void setJawabanUser(int jawabanUser) {
        this.jawabanUser = jawabanUser;
    }
}
