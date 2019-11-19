package com.example.dblearning.assets.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.dblearning.assets.database.model.TableJawabanTeoriUser;

import java.util.List;

@Dao
public interface JawabanTeoriUserDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertJawabanTeoriUser(TableJawabanTeoriUser tableJawabanTeoriUser);
    @Query("UPDATE tablejawabanteoriuser SET jawabanUser=:jawaban WHERE soalId=:soal")
    void updateJawabanTeoriUser(int jawaban,int soal);
    @Query("SELECT * FROM TableJawabanTeoriUser WHERE soalId=:id_soal")
    TableJawabanTeoriUser getJawabanUser(int id_soal);
    @Query("SELECT COUNT(*) FROM TableJawabanTeoriUser")
    int getTotalJawabanTeoriUser();
    @Query("SELECT * FROM TableJawabanTeoriUser")
    List<TableJawabanTeoriUser> getAllJawabanUser();
    @Query("DELETE FROM TableJawabanTeoriUser")
    void deleteAllJawabanTeoriUser();
}
