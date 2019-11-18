package com.example.dblearning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class LoadingActivity extends AppCompatActivity {

    private final int SPLASH_DISPLAY_LENGTH = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                Intent it = new Intent(LoadingActivity.this, MainMenu.class);
                startActivity(it);
            }
        }, SPLASH_DISPLAY_LENGTH) ;
    }
}
