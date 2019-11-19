package com.example.dblearning.assets.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.dblearning.assets.database.model.TableTeori;

import java.util.List;

@Dao
public interface TeoriDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTeori(TableTeori tableTeori);
    @Query("SELECT COUNT(*) FROM TableTeori")
    int getTotal();
    @Query("SELECT * FROM TableTeori")
    List<TableTeori> getAllTeori();
    @Query("DELETE FROM TableTeori")
    void deleteAllTeori();
}
