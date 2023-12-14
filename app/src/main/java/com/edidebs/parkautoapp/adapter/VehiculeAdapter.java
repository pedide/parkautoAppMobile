package com.edidebs.parkautoapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.edidebs.parkautoapp.R;
import com.edidebs.parkautoapp.entity.VehiculeEntity;

import java.util.List;

public class VehiculeAdapter extends RecyclerView.Adapter<VehiculeHolder> {

    private List<VehiculeEntity> vehiculeList;

    public VehiculeAdapter(List<VehiculeEntity> vehiculeList) {
        this.vehiculeList = vehiculeList;
    }

    public VehiculeAdapter() {
    }

    @NonNull
    @Override
    public VehiculeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_vehicules_item,parent,false);
        return new VehiculeHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VehiculeHolder holder, int position) {

        VehiculeEntity vehiculeEntity = vehiculeList.get(position);
        holder.modelVehicule.setText(vehiculeEntity.getModelVehicule());
        holder.anneeModel.setText(Integer.toString(vehiculeEntity.getAnneeModel()));
        holder.descriptif.setText(vehiculeEntity.getDescriptif());
        holder.prix.setText(String.format(Double.toString(vehiculeEntity.getPrix())));
        holder.imageVehicule.setText(vehiculeEntity.getImageVehicule());

    }

    @Override
    public int getItemCount() {
        return vehiculeList.size();
    }
}
