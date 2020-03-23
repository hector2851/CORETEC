package com.example.coretec;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class RegistroActivity extends AppCompatActivity {

    private EditText eTextEmail;
    private EditText eTextPassword;
    private EditText eTextNombre;
    private EditText eTextApellido;
    private EditText eTextEdad;
    private Button ebtn_Registrar;

    //ANIMACION
    private TextInputLayout eTextEmailQ;
    private TextInputLayout eTextPasswordW;
    private TextInputLayout eTextNombreE;
    private TextInputLayout eTextApellidoR;
    private TextInputLayout eTextEdadT;
    Animation fromtop, fromBotton;

    //Variable sde datos a registra
    private String Nombres = "";
    private String Apellidos = "";
    private String Email = "";
    private String Password = "";
    private String Edad = "";

    FirebaseAuth mAuth;
    DatabaseReference mDatabase;
    private ProgressDialog mDialogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        getSupportActionBar().setTitle("Registro de Usuario");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //ANIMACION
        eTextEmailQ = (TextInputLayout) findViewById(R.id.TextEmailQ);
        eTextPasswordW = (TextInputLayout) findViewById(R.id.TextPasswordW);
        eTextNombreE = (TextInputLayout) findViewById(R.id.TextNombreE);
        eTextApellidoR = (TextInputLayout) findViewById(R.id.TextApellidoR);
        eTextEdadT = (TextInputLayout) findViewById(R.id.TextEdadT);
        ebtn_Registrar = (Button) findViewById(R.id.btn_Registrar);

        fromtop = AnimationUtils.loadAnimation(this, R.anim.fromimg);
        fromBotton = AnimationUtils.loadAnimation(this, R.anim.fromboton);
        ebtn_Registrar.setAnimation(fromBotton);
        eTextEmailQ.setAnimation(fromtop);
        eTextPasswordW.setAnimation(fromtop);
        eTextNombreE.setAnimation(fromtop);
        eTextApellidoR.setAnimation(fromtop);
        eTextEdadT.setAnimation(fromtop);



        //FIREBASE
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDialogo = new ProgressDialog(this);

        eTextEmail = (EditText) findViewById(R.id.TextEmail);
        eTextPassword = (EditText) findViewById(R.id.TextPassword);
        eTextNombre = (EditText) findViewById(R.id.TextNombre);
        eTextApellido = (EditText) findViewById(R.id.TextApellido);
        eTextEdad = (EditText) findViewById(R.id.TextEdad);
        ebtn_Registrar = (Button) findViewById(R.id.btn_Registrar);

        ebtn_Registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Nombres = eTextNombre.getText().toString();
                Apellidos = eTextApellido.getText().toString();
                Email = eTextEmail.getText().toString();
                Password = eTextPassword.getText().toString();
                Edad = eTextEdad.getText().toString();


                if (!Nombres.isEmpty() && !Apellidos.isEmpty() && !Email.isEmpty() && !Password.isEmpty() && !Edad.isEmpty()){

                    if (Password.length() >= 6){
                        mDialogo.setTitle("Espere un momento");
                        mDialogo.setMessage("Estamos registrando los datos...");
                        mDialogo.setCanceledOnTouchOutside(false);
                        mDialogo.show();
                        registerUser();
                    }else {
                        Toast.makeText(RegistroActivity.this, "La contraseña debe tener al menos 6 digitos", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(RegistroActivity.this, "Debe Completar todos los campos!", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
    private  void registerUser(){

        mAuth.createUserWithEmailAndPassword(Email, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){

                    Map<String,Object> map = new HashMap<>();
                    map.put("Nombres", Nombres);
                    map.put("Apellidos", Apellidos);
                    map.put("Email", Email);
                    map.put("Password", Password);
                    map.put("Edad", Edad);

                    String id = mAuth.getCurrentUser().getUid();
                    mDatabase.child("Usuario").child(id).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task2) {
                            if (task2.isSuccessful()) {
                                startActivity(new Intent(RegistroActivity.this, LoginActivity.class));
                                Toast.makeText(RegistroActivity.this, "Listol! Ahora Ingresa tu cuenta =)! ", Toast.LENGTH_SHORT).show();
                                mDialogo.dismiss();
                                finish();
                            }else{
                                Toast.makeText(RegistroActivity.this, "No se pudieron crear los datos correctamente", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }else {
                    Toast.makeText(RegistroActivity.this, "No se pudo Registrar este Usuario", Toast.LENGTH_SHORT).show();
                }
            }

        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mDialogo.dismiss();
    }
}
