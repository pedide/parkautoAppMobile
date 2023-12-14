package com.edidebs.parkautoapp.repository;



import com.edidebs.parkautoapp.entity.LoginResponse;
import com.edidebs.parkautoapp.entity.UserEntity;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserRepository {

    @POST("/api/auth/signin")
    Call<LoginResponse> userLogin(@Body UserEntity userEntity);

    @POST("/api/auth/signup")
    Call<LoginResponse> userRegister(@Body UserEntity userEntity);
}
