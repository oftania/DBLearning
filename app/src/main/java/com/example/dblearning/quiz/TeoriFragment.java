package com.example.dblearning.quiz;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dblearning.R;
import com.example.dblearning.assets.AppData;
import com.example.dblearning.assets.PrefManager;
import com.example.dblearning.assets.adapter.SoalTeoriAdapter;
import com.example.dblearning.assets.database.JawabanTeoriDatabase;
import com.example.dblearning.assets.database.JawabanTeoriUserDatabase;
import com.example.dblearning.assets.database.TeoriDatabase;
import com.example.dblearning.assets.database.model.TableJawabanTeori;
import com.example.dblearning.assets.database.model.TableTeori;
import com.example.dblearning.assets.model.Jawaban;
import com.example.dblearning.assets.model.SoalTeori;
import com.example.dblearning.assets.retrofit.ApiServices;
import com.example.dblearning.assets.retrofit.ApiUtils;
import com.example.dblearning.assets.retrofit.response.ResponSoalTeori;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class TeoriFragment extends Fragment {
    private Context context;
    private View rootView;
    private SwipeRefreshLayout swipTeori;
    private RecyclerView rvTeori;
    private RecyclerView.Adapter mAdapter;
    private ApiServices apiServices = ApiUtils.getApiServices();
    private CardView btnUlangi,btnLihatHasil;
    private TextView tvLihatHasil;
    private TeoriDatabase teoriDatabase;
    private JawabanTeoriDatabase jawabanTeoriDatabase;
    private JawabanTeoriUserDatabase jawabanTeoriUserDatabase;
    private PrefManager prefManager;

    public TeoriFragment() {
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
        rootView = inflater.inflate(R.layout.fragment_teori, container, false);
        initDatabase();
        initComponent();
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        //cek apakah soal sudah ada atau belum
        if (teoriDatabase.teoriDAO().getTotal()!=0){
            //apabila memiliki data
            loadDatabaseData();
        }else{
            getDataFromApi();
        }
        checkCompleteQuiz();
    }

    private void checkCompleteQuiz() {
        if (!prefManager.checkFInishLatihan()){
            btnUlangi.setVisibility(View.GONE);
            btnLihatHasil.setVisibility(View.VISIBLE);
            tvLihatHasil.setText("Simpan Jawaban");
        }else{
            btnUlangi.setVisibility(View.VISIBLE);
            btnLihatHasil.setVisibility(View.VISIBLE);
            tvLihatHasil.setText("Lihat Hasil");
        }
    }

    private void getDataFromApi() {
        swipTeori.setRefreshing(true);
        apiServices.getSoalTeori(AppData.TOTAL_TEORI).enqueue(new Callback<ResponSoalTeori>() {
            @Override
            public void onResponse(@NotNull Call<ResponSoalTeori> call, @NotNull Response<ResponSoalTeori> response) {
                if (response.isSuccessful()){
                    try{
                        if (response.body().isStatus()){
                            //inset into database
                            //insert soal
                            for (int i=0;i<response.body().getTeoriList().size();i++) {
                                SoalTeori teori = response.body().getTeoriList().get(i);
                                teoriDatabase.teoriDAO().insertTeori(new TableTeori(Integer.parseInt(teori.getIdBankSoal()), teori.getButirSoal()));
                                for (int j = 0; j < teori.getJawaban().size(); j++) {
                                    Jawaban jawabanTeori = teori.getJawaban().get(j);
                                    boolean statusjawaban = false;
                                    if (jawabanTeori.getKet_jawaban().equals("1")) {
                                        statusjawaban = true;
                                    }
                                    jawabanTeoriDatabase.jawabanTeoriDAO().insertDataTeori(new TableJawabanTeori(Integer.parseInt(jawabanTeori.getIdBankjawaban()), jawabanTeori.getJawaban(), Integer.parseInt(jawabanTeori.getBankSoalId()), statusjawaban));
                                }
                            }
                            //finish simpan ke database
                            loadDatabaseData();
                        }else{
                            Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }catch (Exception e){
                        swipTeori.setRefreshing(false);
                        e.printStackTrace();
                        Toast.makeText(context, "terjadi kesalahan pada aplikasi", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(context, "Gagal terhubung ke server", Toast.LENGTH_SHORT).show();
                    swipTeori.setRefreshing(false);
                }
            }

            @Override
            public void onFailure(@NotNull Call<ResponSoalTeori> call, @NotNull Throwable t) {
                t.printStackTrace();
                swipTeori.setRefreshing(false);
            }
        });
    }

    private void loadDatabaseData() {
        swipTeori.setRefreshing(true);
        mAdapter = new SoalTeoriAdapter(
                context,
                teoriDatabase.teoriDAO().getAllTeori(),
                jawabanTeoriDatabase,
                jawabanTeoriUserDatabase);
        rvTeori.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
        rvTeori.post(new Runnable() {
            @Override
            public void run() {
                mAdapter.notifyDataSetChanged();
                swipTeori.setRefreshing(false);
            }
        });
        btnLihatHasil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (jawabanTeoriUserDatabase.jawabanTeoriUserDAO().getTotalJawabanTeoriUser()==teoriDatabase.teoriDAO().getTotal()){
                    prefManager.simpanJawabanTeori();
                    startActivity(new Intent(context,LihatHasilActivity.class));
                }else{
                    Toast.makeText(context, "Anda Harus menyelesaikan latihan, sebelum menyimpan jawaban..", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnUlangi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (jawabanTeoriUserDatabase.jawabanTeoriUserDAO().getTotalJawabanTeoriUser()==teoriDatabase.teoriDAO().getTotal()){
                    resetALLData();
                }else{
                    Toast.makeText(context, "Anda Harus menyelesaikan latihan sebelum memulai baru..", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void resetALLData() {
        teoriDatabase.teoriDAO().deleteAllTeori();
        jawabanTeoriUserDatabase.jawabanTeoriUserDAO().deleteAllJawabanTeoriUser();
        jawabanTeoriDatabase.jawabanTeoriDAO().deleteJawabanTeori();
        prefManager.clearAllData();
        onResume();

    }

    private void initDatabase() {
        teoriDatabase = Room.databaseBuilder(context, TeoriDatabase.class,AppData.DATABASE_TEORI)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
        jawabanTeoriDatabase = Room.databaseBuilder(context, JawabanTeoriDatabase.class,AppData.DATAVASE_JAWABAN_TEORI)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
        jawabanTeoriUserDatabase = Room.databaseBuilder(context, JawabanTeoriUserDatabase.class,AppData.DATABASE_JAWABAN_TEORI_USER)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
    }

    private void initComponent() {
        prefManager = new PrefManager(context);
        swipTeori = rootView.findViewById(R.id.swip_teori);
        rvTeori = rootView.findViewById(R.id.rv_teori);
        rvTeori.setLayoutManager(new LinearLayoutManager(context));
        btnLihatHasil = rootView.findViewById(R.id.btn_lihat_hasil);
        btnUlangi = rootView.findViewById(R.id.btn_ulangi);
        tvLihatHasil =rootView.findViewById(R.id.tv_lihat_hasil);
        swipTeori.setOnRefreshListener(() -> {
            onAttach(context);
            if (teoriDatabase.teoriDAO().getTotal()!=0){
                loadDatabaseData();
            }else{
                getDataFromApi();
            }
        });
    }

}
