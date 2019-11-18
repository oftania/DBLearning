package com.example.dblearning;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.dblearning.assets.adapter.MateriAdapter;
import com.example.dblearning.assets.model.Materi;
import com.example.dblearning.assets.retrofit.ApiServices;
import com.example.dblearning.assets.retrofit.ApiUtils;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MateriActivity extends AppCompatActivity {
    @BindView(R.id.rv_materi)
    RecyclerView rvMateri;
    RecyclerView.Adapter mAdapter;
    @BindView(R.id.swip_materi)
    SwipeRefreshLayout swipMateri;
    private Context context;
    private ApiServices apiServices = ApiUtils.getApiServices();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materi);
        ButterKnife.bind(this);
        context = this;
        initComponent();
        loadData();
    }

    private void loadData() {
        swipMateri.setRefreshing(true);
        apiServices.getAllMateri().enqueue(new Callback<List<Materi>>() {
            @Override
            public void onResponse(@NotNull Call<List<Materi>> call, @NotNull Response<List<Materi>> response) {
                if (response.isSuccessful()){
                    try{
                        mAdapter = new MateriAdapter(context,response.body());
                        rvMateri.setAdapter(mAdapter);
                        mAdapter.notifyDataSetChanged();
                        rvMateri.post(() -> swipMateri.setRefreshing(false));
                    }catch (Exception e){
                        swipMateri.setRefreshing(false);
                        e.printStackTrace();
                        Toast.makeText(context, "Terjadi kesalahan pada aplikasi", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    swipMateri.setRefreshing(false);
                    Toast.makeText(context, "gagal terhubung ke server", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NotNull Call<List<Materi>> call, @NotNull Throwable t) {
                swipMateri.setRefreshing(false);
                t.printStackTrace();
                Toast.makeText(context, "Terjadi kesalahan saat menghubungkan ke server", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initComponent() {
        //init toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Materi");
        setSupportActionBar(toolbar);
        if (getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        rvMateri.setLayoutManager(new LinearLayoutManager(context));
        swipMateri.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData();
            }
        });
        rvMateri.setHasFixedSize(true);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
