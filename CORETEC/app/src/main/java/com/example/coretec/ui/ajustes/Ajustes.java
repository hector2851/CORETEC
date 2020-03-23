package com.example.coretec.ui.ajustes;

import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.coretec.Actividades.ResiduosActivity;
import com.example.coretec.Cuadro;
import com.example.coretec.InicioActivity;
import com.example.coretec.MenuActi;
import com.example.coretec.R;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Ajustes extends Fragment {

    private ListView listView1;
    private ListView listView2;
    private List<String> perfil;
    private List<Integer> perfilImg;
    private List<String> logout;
    private List<Integer> logoutImg;
    Context contexto;
    private FirebaseAuth mAuth;
    public Ajustes() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {



        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ajustes, container, false);
        mAuth = FirebaseAuth.getInstance();
        contexto=getContext();

        listView1 = (ListView) view.findViewById(R.id.ListView1);
        perfil = new ArrayList<String>();
        perfil.add("Mi Perfil");

        perfilImg = new ArrayList<Integer>();
        perfilImg.add(R.drawable.ic_friend);

        AjustesAdapter ajustesAdapter = new AjustesAdapter(getContext(), R.layout.ajustes_item, perfil, perfilImg);
        listView1.setAdapter(ajustesAdapter);

        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               // Intent intent = new Intent(getActivity(), ResiduosActivity.class);
                //Toast.makeText(getContext(),"ES HORA DE RECICLAR!", Toast.LENGTH_LONG).show();
                //startActivity(intent);
            }
        });

        listView2 = (ListView) view.findViewById(R.id.ListView2);
        logout = new ArrayList<String>();
        logout.add("Cerrar Sesión");

        logoutImg = new ArrayList<Integer>();
        logoutImg.add(R.drawable.ic_logout);

        AjustesAdapter Adapter = new AjustesAdapter(getContext(), R.layout.ajustes_item, logout, logoutImg);
        listView2.setAdapter(Adapter);

        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CuadroSalir();
                //new Cuadro(contexto);

            }
        });


        return view;
    }

    private void CuadroSalir(){

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.cerrar_dialogo, null);

        builder.setView(view);
        final AlertDialog dialog = builder.create();
        dialog.show();

        Button no = (Button)view.findViewById(R.id.btn_no);
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        Button si = (Button)view.findViewById(R.id.btn_si);
        si.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mAuth.signOut();
                LoginManager.getInstance().logOut();
                updateUI();

            }
        });

    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser ==null){
            updateUI();

        }
    }

    private void updateUI() {
        Toast.makeText(getContext(), "Esperemos que vuelvas pronto!", Toast.LENGTH_LONG).show();
        Intent account = new Intent (getContext(), InicioActivity.class);
        startActivity(account);


    }



}

