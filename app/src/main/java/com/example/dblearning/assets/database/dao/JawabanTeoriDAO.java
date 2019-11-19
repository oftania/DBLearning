package com.example.dblearning.assets.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.dblearning.assets.database.model.TableJawabanTeori;

import java.util.List;

@Dao
public interface JawabanTeoriDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertDataTeori(TableJawabanTeori tableJawabanTeori);
    @Query("SELECT * FROM TableJawabanTeori WHERE bankSoalId=:idsoal")
    List<TableJawabanTeori> getJawabanBySoal(int idsoal);
    @Query("SELECT * FROM TableJawabanTeori WHERE bankSoalId=:idsoal and idJawabanTeori=:idjawaban")
    TableJawabanTeori getJawabanByidSoaldanJawaban(int idsoal,int idjawaban);
    @Query("DELETE FROM TableJawabanTeori")
    void deleteJawabanTeori();
}
