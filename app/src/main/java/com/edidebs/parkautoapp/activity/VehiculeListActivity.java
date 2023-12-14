package com.edidebs.parkautoapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.edidebs.parkautoapp.R;
import com.edidebs.parkautoapp.adapter.VehiculeAdapter;
import com.edidebs.parkautoapp.entity.VehiculeEntity;
import com.edidebs.parkautoapp.repository.VehiculeRepository;
import com.edidebs.parkautoapp.retrofit.RetrofitVehicule;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VehiculeListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicule_list);

        recyclerView = findViewById(R.id.vehiculeList_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        loadVehicules();
    }

    private void loadVehicules() {
        VehiculeEntity vehiculeEntity = new VehiculeEntity();
        //Injection des données à partir du retrofit
        RetrofitVehicule retrofitVehicule = new RetrofitVehicule();
        VehiculeRepository vehiculeRepository = retrofitVehicule.getRetrofit().create(VehiculeRepository.class);
        vehiculeRepository.getAllVehicules()
                .enqueue(new Callback<List<VehiculeEntity>>() {
                    @Override
                    public void onResponse(Call<List<VehiculeEntity>> call, Response<List<VehiculeEntity>> response) {
                        populateListView(response.body());

                    }

                    @Override
                    public void onFailure(Call<List<VehiculeEntity>> call, Throwable t) {
                        Toast.makeText(VehiculeListActivity.this,"Failled to load vehicule!!! ",Toast.LENGTH_SHORT).show();

                    }
                });

    }

    private void populateListView(List<VehiculeEntity> vehiculeEntityList) {
        VehiculeAdapter vehiculeAdapter = new VehiculeAdapter(vehiculeEntityList);
        recyclerView.setAdapter(vehiculeAdapter);

    }
}