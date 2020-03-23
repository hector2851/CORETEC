package com.example.coretec.Actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.coretec.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;

public class Enviar extends AppCompatActivity {

    private EditText fecha;
    private int dia,mes,ano;
    DatePickerDialog datePickerDialog;
    // Calendar C = Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enviar);

        getSupportActionBar().setTitle("Unos pasos más");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        fecha = findViewById(R.id.TextFecha);
        final Calendar C = Calendar.getInstance();
        fecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dia = C.get(Calendar.DAY_OF_MONTH);
                mes = C.get(Calendar.MONTH);
                ano = C.get(Calendar.YEAR);
                datePickerDialog = new DatePickerDialog(Enviar.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        fecha.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                    }
                },ano,mes,dia);
                datePickerDialog.show();
            }
        });
    }
}



