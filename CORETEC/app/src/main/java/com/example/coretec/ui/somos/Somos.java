package com.example.coretec.ui.somos;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.coretec.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Somos extends Fragment {


    public Somos() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_somos, container, false);


        return view;
    }

}
