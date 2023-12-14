package com.edidebs.parkautoapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.edidebs.parkautoapp.R;
import com.edidebs.parkautoapp.entity.VehiculeEntity;
import com.edidebs.parkautoapp.repository.VehiculeRepository;
import com.edidebs.parkautoapp.retrofit.RetrofitVehicule;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        initializeComponents();
    }

    private void initializeComponents() {

        TextInputEditText inputEditTextModelVehicule = findViewById(R.id.form_textFieldModelVehicule);
        TextInputEditText inputEditTextAnneeModel =  findViewById(R.id.form_textFieldAnneeModel);
        TextInputEditText inputEditTextDescription =  findViewById(R.id.form_textFieldDescription);
        TextInputEditText inputEditTextPrix = findViewById(R.id.form_textFieldPrix);
        TextInputEditText inputEditTextImage = findViewById(R.id.form_textFieldImage);

        MaterialButton buttonSave = findViewById(R.id.form_button_save_vehicules);

        //Injection des données à partir du retrofit
        RetrofitVehicule retrofitVehicule = new RetrofitVehicule();
        VehiculeRepository vehiculeRepository = retrofitVehicule.getRetrofit().create(VehiculeRepository.class);

        buttonSave.setOnClickListener(
                view -> {
                    String modelVehicule = String.valueOf(inputEditTextModelVehicule.getText());
                    int anneeModel = Integer.parseInt(inputEditTextAnneeModel.getText().toString());
                    String description = String.valueOf(inputEditTextDescription.getText());
                    double prix = Double.parseDouble(inputEditTextPrix.getText().toString());
                    String imageVehi = String.valueOf(inputEditTextImage.getText());

                    VehiculeEntity vehiculeEntity = new VehiculeEntity();
                    vehiculeEntity.setModelVehicule(modelVehicule);
                    vehiculeEntity.setAnneeModel(anneeModel);
                    vehiculeEntity.setDescriptif(description);
                    vehiculeEntity.setPrix(prix);
                    vehiculeEntity.setImageVehicule(imageVehi);

                    vehiculeRepository.saveVehicules(vehiculeEntity)
                            .enqueue(
                                    new Callback<VehiculeEntity>() {
                                        @Override
                                        public void onResponse(Call<VehiculeEntity> call, Response<VehiculeEntity> response) {
                                            Toast.makeText(MainActivity.this,"Saved successfull !!! ",Toast.LENGTH_SHORT).show();

                                        }

                                        @Override
                                        public void onFailure(Call<VehiculeEntity> call, Throwable t) {
                                            Toast.makeText(MainActivity.this,"Save failled !!! ",Toast.LENGTH_SHORT).show();
                                            Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE,"Error occurred",t);

                                        }
                                    }
                            );



                }


        );


    }
}