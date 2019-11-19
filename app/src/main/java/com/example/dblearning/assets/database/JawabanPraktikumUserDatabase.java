package com.example.dblearning.assets.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.dblearning.assets.AppData;
import com.example.dblearning.assets.database.dao.JawabanPraktikumUserDAO;
import com.example.dblearning.assets.database.model.TableJawabanPraktikumUser;

@Database(entities = {TableJawabanPraktikumUser.class},version = AppData.DATABASE_VERSION)
public abstract class JawabanPraktikumUserDatabase extends RoomDatabase {
    public abstract JawabanPraktikumUserDAO jawabanPraktikumUserDAO();
}
