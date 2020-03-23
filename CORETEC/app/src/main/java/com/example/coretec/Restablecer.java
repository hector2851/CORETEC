package com.example.coretec;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class Restablecer extends AppCompatActivity {

    private EditText mREmail;
    private Button mbtnReset;
    private String email = "";
    private FirebaseAuth mAuth;

    private ProgressDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restablecer);

        getSupportActionBar().setTitle("¿Olvidaste tu Contraseña?");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mREmail = (EditText) findViewById(R.id.REmail);
        mbtnReset = (Button) findViewById(R.id.btnReset);
        mAuth = FirebaseAuth.getInstance();
        mDialog = new ProgressDialog(this);

        mbtnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                email = mREmail.getText().toString();

                if (!email.isEmpty()){
                    mDialog.setTitle("Espere un momento");
                    mDialog.setMessage("Estamos enviando un correo a su buzon...");
                    mDialog.setCanceledOnTouchOutside(false);
                    mDialog.show();
                    ResetPassword();
                }
                else{
                    Toast.makeText(Restablecer.this, "Ingrese su Correo Electronico", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void ResetPassword(){
        mAuth.setLanguageCode("es");
        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    startActivity(new Intent(Restablecer.this, LoginActivity.class));
                    Toast.makeText(Restablecer.this, "Se envio un correo para restablecer su contraseña, verifique su buzon de correo.", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(Restablecer.this, "El correo ingresado no existe, verifique su correo.", Toast.LENGTH_LONG).show();
                }
                mDialog.dismiss();
            }
        });
    }
}
