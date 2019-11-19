package com.example.dblearning.quiz;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dblearning.R;
import com.example.dblearning.assets.AppData;
import com.example.dblearning.assets.database.JawabanPraktikumUserDatabase;
import com.example.dblearning.assets.database.PraktikumDatabase;

/**
 * A simple {@link Fragment} subclass.
 */
public class PraktikumFragment extends Fragment {
    private Context context;
    private View rootView;
    private SwipeRefreshLayout swipPraktikum;
    private RecyclerView rvPraktikum;
    private PraktikumDatabase praktikumDatabase;
    private JawabanPraktikumUserDatabase jawabanPraktikumUserDatabase;
    private RecyclerView.Adapter mAdapter;


    public PraktikumFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_praktikum, container, false);
        initDatabase();
        initComponent();
        return  rootView;
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    private void initDatabase() {
        praktikumDatabase = Room.databaseBuilder(context, PraktikumDatabase.class, AppData.DATAVASE_PRAKTIKUM)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
        jawabanPraktikumUserDatabase = Room.databaseBuilder(context, JawabanPraktikumUserDatabase.class, AppData.DATABASE_JAWABAN_PRAKTIKUM_USER)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
    }

    private void initComponent() {
        swipPraktikum = rootView.findViewById(R.id.swip_praktikum);
        rvPraktikum = rootView.findViewById(R.id.rv_praktikum);
        rvPraktikum.setLayoutManager(new LinearLayoutManager(context));
    }

}
