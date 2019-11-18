package com.example.dblearning.assets.retrofit;

import com.example.dblearning.assets.model.Materi;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiServices {
    // get all materi
    @GET("materi")
    Call<List<Materi>> getAllMateri();
}
