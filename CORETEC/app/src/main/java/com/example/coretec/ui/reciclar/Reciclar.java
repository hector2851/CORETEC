package com.example.coretec.ui.reciclar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.Toast;

import com.example.coretec.Actividades.ResiduosActivity;
import com.example.coretec.R;

import java.util.ArrayList;
import java.util.List;


public class Reciclar extends Fragment {


    public Reciclar() {
        // Required empty public constructor
    }
    private List<String> raee;
    private List<Integer> raeeImage;

    GridView gridView1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reciclar, container, false);


        gridView1 = (GridView) view.findViewById(R.id.gridView1);
        raee = new ArrayList<String>();
        raee.add("Residuos Electronicos");

        raeeImage = new ArrayList<Integer>();
        raeeImage.add(R.drawable.raee);

        RaeeAdapter raeeAdapter = new RaeeAdapter(getContext(), R.layout.raee_item, raee, raeeImage);
        gridView1.setAdapter(raeeAdapter);

        gridView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
             getActivity().startActivity(new Intent(getActivity(), ResiduosActivity.class));
                //Intent intent = new Intent(getActivity(), ResiduosActivity.class);
              //Toast.makeText(getContext(),"ES HORA DE RECICLAR!", Toast.LENGTH_LONG).show();
              //startActivity(intent);
            }
        });


        return  view;
    }

}

