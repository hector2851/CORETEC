package com.example.coretec.Actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.coretec.R;

public class Datos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos);

        getSupportActionBar().setTitle("Datos");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
}
