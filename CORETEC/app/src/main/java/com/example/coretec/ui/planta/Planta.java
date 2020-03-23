package com.example.coretec.ui.planta;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.coretec.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Planta extends Fragment {


    public Planta() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_planta, container, false);

        return  view;
    }

}
