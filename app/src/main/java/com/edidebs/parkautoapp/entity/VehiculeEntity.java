package com.edidebs.parkautoapp.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

//@Data
//@RequiredArgsConstructor
public class VehiculeEntity {

    private Long id;

    private String modelVehicule;
    private int anneeModel;
    private String descriptif;
    private double prix;
    private String imageVehicule;

    public VehiculeEntity() {
    }

    public VehiculeEntity(Long id, String modelVehicule, int anneeModel, String descriptif, double prix, String imageVehicule) {
        this.id = id;
        this.modelVehicule = modelVehicule;
        this.anneeModel = anneeModel;
        this.descriptif = descriptif;
        this.prix = prix;
        this.imageVehicule = imageVehicule;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModelVehicule() {
        return modelVehicule;
    }

    public void setModelVehicule(String modelVehicule) {
        this.modelVehicule = modelVehicule;
    }

    public int getAnneeModel() {
        return anneeModel;
    }

    public void setAnneeModel(int anneeModel) {
        this.anneeModel = anneeModel;
    }

    public String getDescriptif() {
        return descriptif;
    }

    public void setDescriptif(String descriptif) {
        this.descriptif = descriptif;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getImageVehicule() {
        return imageVehicule;
    }

    public void setImageVehicule(String imageVehicule) {
        this.imageVehicule = imageVehicule;
    }
}
