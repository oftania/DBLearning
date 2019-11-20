package com.example.dblearning.assets.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.dblearning.assets.database.model.TablePraktikum;

import java.util.List;

@Dao
public interface PraktikumDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPraktikum(TablePraktikum tablePraktikum);
    @Query("SELECT COUNT(*) FROM TablePraktikum")
    int getTotalPraktikum();
    @Query("SELECT COUNT(*) FROM TablePraktikum WHERE jawabanUser is not null")
    int getTotalPraktikumTerjawab();
    @Query("SELECT * FROM TablePraktikum")
    List<TablePraktikum> getAllPraktikum();
    @Query("SELECT * FROM TablePraktikum WHERE idPraktikum=:id")
    TablePraktikum getPraktikumById(int id);
    @Query("UPDATE TablePraktikum SET jawabanUser=:jawaban WHERE idPraktikum=:soal")
    void updateJawabanPraktikumUser(String jawaban,int soal);
    @Query("DELETE FROM TablePraktikum")
    void deleteAllPraktikum();
}
