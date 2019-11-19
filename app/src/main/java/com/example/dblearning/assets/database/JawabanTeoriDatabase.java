package com.example.dblearning.assets.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.dblearning.assets.AppData;
import com.example.dblearning.assets.database.dao.JawabanTeoriDAO;
import com.example.dblearning.assets.database.model.TableJawabanTeori;

@Database(entities = {TableJawabanTeori.class}, version = AppData.DATABASE_VERSION)
public abstract class JawabanTeoriDatabase extends RoomDatabase {
    public abstract JawabanTeoriDAO jawabanTeoriDAO();
}
