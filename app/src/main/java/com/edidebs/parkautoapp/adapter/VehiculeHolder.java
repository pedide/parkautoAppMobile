package com.edidebs.parkautoapp.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.edidebs.parkautoapp.R;

public class VehiculeHolder extends RecyclerView.ViewHolder {

    TextView anneeModel, descriptif,prix,imageVehicule,modelVehicule;
    public VehiculeHolder(@NonNull View itemView) {
        super(itemView);
        anneeModel = itemView.findViewById(R.id.vehiculeList_anneeModel);
        descriptif = itemView.findViewById(R.id.vehiculeList_descriptif);
        prix = itemView.findViewById(R.id.vehiculeList_prix);
        imageVehicule = itemView.findViewById(R.id.vehiculeList_imageVehicule);
        modelVehicule = itemView.findViewById(R.id.vehiculeList_modelVehicule);
    }
}
