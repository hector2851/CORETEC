package com.example.coretec;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private EditText mTxtEmail;
    private EditText mTxtPassword;
    private TextInputLayout mTxtEmailX;
    private TextInputLayout mTxtPasswordY;
    private Button mbtnIngresar;
    private TextView mtxtOlvido;
    private ImageView mImgPlaneta;
    private String Email = "";
    private String Password = "";
    Animation fromBotton, fromtop, deBotton, izBotton;;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().setTitle("Iniciar Sesion");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        mAuth = FirebaseAuth.getInstance();
        mTxtEmail = (EditText) findViewById(R.id.TxtEmail);
        mTxtPassword = (EditText) findViewById(R.id.TxtPassword);
        mbtnIngresar = (Button) findViewById(R.id.btnIngresar);

        //ANIMACION
        mtxtOlvido = (TextView) findViewById(R.id.txtOlvido);
        mImgPlaneta = (ImageView) findViewById(R.id.ImgPlaneta);
        mTxtEmailX = (TextInputLayout) findViewById(R.id.TxtEmailX);
        mTxtPasswordY = (TextInputLayout) findViewById(R.id.TxtPasswordY);
        fromBotton = AnimationUtils.loadAnimation(this, R.anim.fromboton);
        deBotton = AnimationUtils.loadAnimation(this, R.anim.deboton);
        izBotton = AnimationUtils.loadAnimation(this, R.anim.izboton);
        fromtop = AnimationUtils.loadAnimation(this, R.anim.fromimg);
        mtxtOlvido.setAnimation(fromBotton);
        mbtnIngresar.setAnimation(fromBotton);
        mTxtPasswordY.setAnimation(deBotton);
        mTxtEmailX.setAnimation(izBotton);
        mImgPlaneta.setAnimation(fromtop);

        mtxtOlvido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, Restablecer.class));
            }
        });

        mbtnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Email = mTxtEmail.getText().toString();
                Password = mTxtPassword.getText().toString();

                if (!Email.isEmpty() && !Password.isEmpty()){
                    loginUser();
                }else {
                    Toast.makeText(LoginActivity.this, "Complete todos los Campos", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    private void loginUser(){
        mAuth.signInWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
               if (task.isSuccessful()){
                   startActivity(new Intent(LoginActivity.this, MenuActi.class));
                   Toast.makeText(LoginActivity.this, "Bienvenido a Coretec" , Toast.LENGTH_SHORT).show();
                   finish();
               }else{
                   Toast.makeText(LoginActivity.this, "El correo o la contraseña son incorrectos, Comprueba tus datos", Toast.LENGTH_SHORT).show();
               }
            }
        });
    }

}
