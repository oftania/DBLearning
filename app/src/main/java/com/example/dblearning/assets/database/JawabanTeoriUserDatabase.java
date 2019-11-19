package com.example.dblearning.assets.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.dblearning.assets.AppData;
import com.example.dblearning.assets.database.dao.JawabanTeoriUserDAO;
import com.example.dblearning.assets.database.model.TableJawabanTeoriUser;

@Database(entities = {TableJawabanTeoriUser.class},version = AppData.DATABASE_VERSION)
public abstract class JawabanTeoriUserDatabase extends RoomDatabase {
    public abstract JawabanTeoriUserDAO jawabanTeoriUserDAO();
}
