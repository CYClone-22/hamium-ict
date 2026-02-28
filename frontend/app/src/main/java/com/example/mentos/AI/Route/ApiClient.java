package com.example.mentos.AI.Route;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.concurrent.TimeUnit;

public class ApiClient {

    private static final String BASE_URL = "http://52.79.234.36:5001/";
    private static Retrofit retrofit = null;

    public static ChatGPTApi getChatGPTApi() {
        if (retrofit == null) {
            // LoggingInterceptor 설정
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY); // 로그 레벨 설정

            // OkHttpClient 설정
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(60, TimeUnit.SECONDS)  // 연결 타임아웃
                    .writeTimeout(60, TimeUnit.SECONDS)    // 쓰기 타임아웃
                    .readTimeout(60, TimeUnit.SECONDS)     // 읽기 타임아웃
                    .addInterceptor(loggingInterceptor)    // LoggingInterceptor 추가
                    .build();

            // Retrofit 설정
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)  // OkHttpClient 추가
                    .build();
        }

        return retrofit.create(ChatGPTApi.class);
    }
}
