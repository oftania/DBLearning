package com.example.dblearning.assets.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.dblearning.assets.AppData;
import com.example.dblearning.assets.database.dao.PraktikumDAO;
import com.example.dblearning.assets.database.model.TablePraktikum;

@Database(entities = {TablePraktikum.class},version = AppData.DATABASE_VERSION)
public abstract class PraktikumDatabase extends RoomDatabase {
    public abstract PraktikumDAO praktikumDAO();
}
