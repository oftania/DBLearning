package com.example.dblearning.assets.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

import com.example.dblearning.assets.database.model.TableJawabanPraktikumUser;
import com.example.dblearning.assets.database.model.TablePraktikum;

@Dao
public interface JawabanPraktikumUserDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertJawabanPraktikumUser(TableJawabanPraktikumUser tableJawabanPraktikumUser);
}
