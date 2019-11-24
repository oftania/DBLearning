package com.example.dblearning.quiz;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.dblearning.R;
import com.example.dblearning.assets.AppData;
import com.example.dblearning.assets.adapter.SoalPraktikumAdapter;
import com.example.dblearning.assets.database.PraktikumDatabase;
import com.example.dblearning.assets.database.model.TablePraktikum;
import com.example.dblearning.assets.model.SoalPraktikum;
import com.example.dblearning.assets.retrofit.ApiServices;
import com.example.dblearning.assets.retrofit.ApiUtils;
import com.example.dblearning.assets.retrofit.response.ResponSoalPraktikum;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class PraktikumFragment extends Fragment {
    private Context context;
    private View rootView;
    private RecyclerView rvPraktikum;
    private CardView btnUlangi;
    private PraktikumDatabase praktikumDatabase;
    private RecyclerView.Adapter mAdapter;
    private ApiServices apiServices = ApiUtils.getApiServices();


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
        loadData();
    }

    private void initDatabase() {
        praktikumDatabase = Room.databaseBuilder(context, PraktikumDatabase.class, AppData.DATAVASE_PRAKTIKUM)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
    }

    private void initComponent() {
        rvPraktikum = rootView.findViewById(R.id.rv_praktikum);
        rvPraktikum.setLayoutManager(new LinearLayoutManager(context));
        btnUlangi = rootView.findViewById(R.id.btn_ulangi);
        btnUlangi.setOnClickListener(v -> {
            if (praktikumDatabase.praktikumDAO().getTotalPraktikum()==praktikumDatabase.praktikumDAO().getTotalPraktikumTerjawab()){
                resetAllData();
            }else{
                Toast.makeText(context, "Selesaikan latihan terlebih dahulu.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void resetAllData() {
        praktikumDatabase.praktikumDAO().deleteAllPraktikum();
        onResume();
    }

    private void getDataFromApi() {
        apiServices.getSoalPraktikum(AppData.TOTAL_PRAKTIKUM).enqueue(new Callback<ResponSoalPraktikum>() {
            @Override
            public void onResponse(@NotNull Call<ResponSoalPraktikum> call, @NotNull Response<ResponSoalPraktikum> response) {
                if (response.isSuccessful()){
                    try{
                        assert response.body() != null;
                        if (response.body().isStatus()){
                            for (int i=0;i<response.body().getPraktikumList().size();i++){
                                SoalPraktikum praktikum = response.body().getPraktikumList().get(i);
                                praktikumDatabase.praktikumDAO().insertPraktikum(new TablePraktikum(Integer.parseInt(praktikum.getIdBankSoalPraktikum()),praktikum.getButirSoal(),praktikum.getJawaban(),null));
                            }
                            loadDatabaseData();
                        }else{
                            Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                        Toast.makeText(context, "terjadi kesalahan pada aplikasi", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(context, "Gagal terhubung ke server", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NotNull Call<ResponSoalPraktikum> call, @NotNull Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void loadDatabaseData() {
        mAdapter = new SoalPraktikumAdapter(context,praktikumDatabase.praktikumDAO().getAllPraktikum(),praktikumDatabase,this);
        rvPraktikum.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
        rvPraktikum.post(() -> mAdapter.notifyDataSetChanged());
        //check total data
        if (praktikumDatabase.praktikumDAO().getTotalPraktikum()==praktikumDatabase.praktikumDAO().getTotalPraktikumTerjawab()){
            btnUlangi.setVisibility(View.VISIBLE);
        }else{
            btnUlangi.setVisibility(View.GONE);
        }
    }

    public void loadData() {
        if (praktikumDatabase.praktikumDAO().getTotalPraktikum()!=0){
            loadDatabaseData();
        }else{
            getDataFromApi();
        }
    }

}
