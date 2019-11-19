package com.example.dblearning.assets.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.dblearning.assets.AppData;
import com.example.dblearning.assets.database.dao.TeoriDAO;
import com.example.dblearning.assets.database.model.TableTeori;

@Database(entities = {TableTeori.class},version = AppData.DATABASE_VERSION)
public abstract class TeoriDatabase extends RoomDatabase {
    public abstract TeoriDAO teoriDAO();
}
