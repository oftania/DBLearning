package com.example.dblearning.assets;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

public class PrefManager {
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    private static final String PREF_NAME = "data_eventku";

    private static final String SIMPAN_JAWABAN_TEORI = "simpan_jawaban_teori";


    @SuppressLint("CommitPrefEdits")
    public PrefManager(Context context) {
        pref = context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        editor = pref.edit();
    }

    public void simpanJawabanTeori(){
        editor.putBoolean(SIMPAN_JAWABAN_TEORI,true);
        editor.commit();
    }

    public boolean checkFInishLatihan(){
        return pref.getBoolean(SIMPAN_JAWABAN_TEORI,false);
    }

    public void clearAllData(){
        editor.clear();
        editor.commit();
    }


}
