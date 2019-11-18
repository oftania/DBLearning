package com.example.dblearning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainMenu extends AppCompatActivity {

    ImageButton btn_materi, btn_latihan, btn_bantuan, btn_tentang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        this.btn_materi = (ImageButton) findViewById(R.id.btn_materi);
        this.btn_latihan = (ImageButton) findViewById(R.id.btn_latihan);
        this.btn_bantuan = (ImageButton) findViewById(R.id.btn_bantuan);
        this.btn_tentang = (ImageButton) findViewById(R.id.btn_tentang);

        btn_tentang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent int_tentang = new Intent(getApplicationContext(), Tentang.class);
                startActivity(int_tentang);
            }
        });
//contoh
        btn_materi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainMenu.this,MateriActivity.class));
            }
        });
        btn_latihan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainMenu.this,QuizActivity.class));
            }
        });
    }
}
