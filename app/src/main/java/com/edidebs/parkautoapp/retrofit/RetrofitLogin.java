package com.edidebs.parkautoapp.retrofit;

import com.edidebs.parkautoapp.repository.UserRepository;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitLogin {
    private static Retrofit getRetrofit(){
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://192.168.218.15:8085")
                .client(okHttpClient)
                .build();

        return retrofit;
    }

    public static UserRepository getUserRepository() {
        UserRepository userRepository = getRetrofit().create(UserRepository.class);
        return userRepository;
    }
}
