package com.example.dblearning.assets.retrofit;

import com.example.dblearning.assets.AppData;

public class ApiUtils {
    private static String BASE_URL = AppData.URL;

    public static ApiServices getApiServices(){
        return RetrofitClient.getClient(BASE_URL).create(ApiServices.class);
    }
}
