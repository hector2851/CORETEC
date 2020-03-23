package com.example.coretec;



import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.view.MenuItem;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.example.coretec.ui.ajustes.Ajustes;
import com.example.coretec.ui.contactanos.Contactanos;
import com.example.coretec.ui.historial.Historial;
import com.example.coretec.ui.planta.Planta;
import com.example.coretec.ui.porqueReci.PorqueReci;
import com.example.coretec.ui.raee.Raee;
import com.example.coretec.ui.reciclar.Reciclar;
import com.example.coretec.ui.somos.Somos;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MenuActi extends AppCompatActivity {

    //FACEBOOK FIREBASE
    private TextView users;
    private TextView emails;
    private ImageView foto;
    //FIREBASE EMAIL PASSWORD
    private TextView Nombre;
    private TextView Apellido;
    private TextView Correo;
    //FIREBASE GOOGLE
    private TextView Nombres;
    private TextView Correos;
    private ImageView fotos;
    private AppBarConfiguration mAppBarConfiguration;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //login FACEBOOK = mostrar datos en menu lateral
        users = (TextView) findViewById(R.id.profile_firstName);
        emails = (TextView) findViewById(R.id.profile_email);
        foto = (ImageView) findViewById(R.id.profile_pic);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user !=null){
            String name = user.getDisplayName();
            String email = user.getEmail();
            String photoUri = String.valueOf(user.getPhotoUrl());

            users.setText(name);
            emails.setText(email);
            Picasso.get().load(photoUri).into(foto);

        }else{
            startActivity(new Intent(MenuActi.this, InicioActivity.class));
        }

        //login GOOGLE
        Nombres = (TextView) findViewById(R.id.profile_firstName);
        Correos = (TextView) findViewById(R.id.profile_email);
        fotos = (ImageView) findViewById(R.id.profile_pic);
        FirebaseUser users = FirebaseAuth.getInstance().getCurrentUser();
        if (users !=null){
            String name = users.getDisplayName();
            String email = users.getEmail();
            String photoUri = String.valueOf(users.getPhotoUrl());

            Nombres.setText(name);
            Correos.setText(email);
            Picasso.get().load(photoUri).into(fotos);

        }else{
            startActivity(new Intent(MenuActi.this, InicioActivity.class));
        }

        //LOGIN FIREBASE
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        Nombre = (TextView) findViewById(R.id.profile_firstName);
        Apellido = (TextView) findViewById(R.id.profile_lastName);
        Correo = (TextView) findViewById(R.id.profile_email);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        //HABILITAR COLORES DE ICONOCOS
        navigationView.setItemIconTintList(null);

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_reciclar,
                R.id.nav_raee,
                R.id.nav_porqueReci,
                R.id.nav_historial,
                R.id.nav_somos,
                R.id.nav_planta,
                R.id.nav_contactanos,
                R.id.nav_ajustes,
                R.id.nav_cerrarSesion)
                .setDrawerLayout(drawer)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        //ASIGNAR EL FRAGMENT A MOSTRAR en INICIO
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.contenedor,new Reciclar()).commit();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                int id = menuItem.getItemId();

                FragmentManager fragmentManager = getSupportFragmentManager();
                if (id ==R.id.nav_reciclar){
                    fragmentManager.beginTransaction().replace(R.id.contenedor,new Reciclar()).commit();
                    getSupportActionBar().setTitle("CORETEC");
                } else if (id == R.id.nav_historial) {
                    fragmentManager.beginTransaction().replace(R.id.contenedor, new Historial()).commit();
                    getSupportActionBar().setTitle("Historial de Reciclaje");
                } else if (id == R.id.nav_raee) {
                    fragmentManager.beginTransaction().replace(R.id.contenedor, new Raee()).commit();
                    getSupportActionBar().setTitle("Que son los RAEE");
                } else if (id == R.id.nav_porqueReci) {
                    fragmentManager.beginTransaction().replace(R.id.contenedor, new PorqueReci()).commit();
                    getSupportActionBar().setTitle("Porque Reciclar RAEE");
                }  else if (id == R.id.nav_planta) {
                    fragmentManager.beginTransaction().replace(R.id.contenedor, new Planta()).commit();
                    getSupportActionBar().setTitle("Puntos de Acopio");
                } else if (id == R.id.nav_somos) {
                    fragmentManager.beginTransaction().replace(R.id.contenedor, new Somos()).commit();
                    getSupportActionBar().setTitle("Conocenos un poco más");
                } else if (id == R.id.nav_contactanos) {
                    fragmentManager.beginTransaction().replace(R.id.contenedor, new Contactanos()).commit();
                    getSupportActionBar().setTitle("Contactanos");
                } else if (id == R.id.nav_ajustes) {
                    fragmentManager.beginTransaction().replace(R.id.contenedor, new Ajustes()).commit();
                    getSupportActionBar().setTitle("Ajustes de Usuario");
                }
                DrawerLayout drawer = findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });

       getUserInfo();
    }


    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    private void getUserInfo(){
        String id = mAuth.getCurrentUser().getUid();
        mDatabase.child("Usuario").child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    String Nombres = dataSnapshot.child("Nombres").getValue().toString();
                    String Apellidos = dataSnapshot.child("Apellidos").getValue().toString();
                    String Email = dataSnapshot.child("Email").getValue().toString();

                    Nombre.setText(Nombres);
                    Apellido.setText(Apellidos);
                    Correo.setText(Email);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


}
