package com.example.dblearning.quiz;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.dblearning.R;
import com.example.dblearning.assets.AppData;
import com.example.dblearning.assets.adapter.KunciTeoriAdapter;
import com.example.dblearning.assets.adapter.SoalTeoriAdapter;
import com.example.dblearning.assets.database.JawabanTeoriDatabase;
import com.example.dblearning.assets.database.JawabanTeoriUserDatabase;
import com.example.dblearning.assets.database.TeoriDatabase;
import com.google.android.material.appbar.AppBarLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class KunciJawabanActivity extends AppCompatActivity {
    @BindView(R.id.rv_teori_kunci)
    RecyclerView rvKunci;
    private Context context;
    private RecyclerView.Adapter mAdapter;
    private TeoriDatabase teoriDatabase;
    private JawabanTeoriDatabase jawabanTeoriDatabase;
    private JawabanTeoriUserDatabase jawabanTeoriUserDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kunci_jawaban);
        ButterKnife.bind(this);
        context = this;
        initDatabase();
        initComponent();
    }

    private void initComponent() {
        //init toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Latihan");
        setSupportActionBar(toolbar);
        if (getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        rvKunci.setLayoutManager(new LinearLayoutManager(context));
        loadDatabaseData();
    }


    private void initDatabase() {
        teoriDatabase = Room.databaseBuilder(context, TeoriDatabase.class, AppData.DATABASE_TEORI)
                .allowMainThreadQueries()
                .build();
        jawabanTeoriDatabase = Room.databaseBuilder(context, JawabanTeoriDatabase.class, AppData.DATAVASE_JAWABAN_TEORI)
                .allowMainThreadQueries()
                .build();
        jawabanTeoriUserDatabase = Room.databaseBuilder(context, JawabanTeoriUserDatabase.class, AppData.DATABASE_JAWABAN_TEORI_USER)
                .allowMainThreadQueries()
                .build();
    }

    private void loadDatabaseData() {
        mAdapter = new KunciTeoriAdapter(
                context,
                teoriDatabase.teoriDAO().getAllTeori(),
                jawabanTeoriDatabase,
                jawabanTeoriUserDatabase);
        rvKunci.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
        rvKunci.post(() -> {
            mAdapter.notifyDataSetChanged();
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
