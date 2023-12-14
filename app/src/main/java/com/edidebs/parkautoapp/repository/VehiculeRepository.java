package com.edidebs.parkautoapp.repository;

import com.edidebs.parkautoapp.entity.VehiculeEntity;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface VehiculeRepository {
    @GET("/vehicules")
    Call<List<VehiculeEntity>> getAllVehicules();

    @POST("/vehicule")
    Call<VehiculeEntity> saveVehicules(@Body VehiculeEntity vehicules);

}
