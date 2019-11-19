package com.example.dblearning.assets.retrofit;

import com.example.dblearning.assets.model.Materi;
import com.example.dblearning.assets.retrofit.response.ResponSoalPraktikum;
import com.example.dblearning.assets.retrofit.response.ResponSoalTeori;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiServices {
    // get all materi
    @GET("materi")
    Call<List<Materi>> getAllMateri();
    //get Teori
    @FormUrlEncoded
    @POST("quiz/teori")
    Call<ResponSoalTeori> getSoalTeori(@Field("total") String total);
    //ambil data praktikum
    @FormUrlEncoded
    @POST("quiz/praktikum")
    Call<ResponSoalPraktikum> getSoalPraktikum(@Field("total") String total);
}
