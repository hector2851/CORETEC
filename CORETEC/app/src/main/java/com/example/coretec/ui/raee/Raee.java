package com.example.coretec.ui.raee;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.coretec.R;

import ozaydin.serkan.com.image_zoom_view.ImageViewZoom;
import ozaydin.serkan.com.image_zoom_view.ImageViewZoomConfig;

/**
 * A simple {@link Fragment} subclass.
 */
public class Raee extends Fragment {


    public Raee() {
        // Required empty public constructor
    }

    ImageViewZoom imageViewZoom;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_raee, container, false);



        imageViewZoom= view.findViewById(R.id.imgCategoria);
        imageViewZoom.getBase64();
        ImageViewZoomConfig imageViewZoomConfig=new ImageViewZoomConfig();
        imageViewZoomConfig.saveProperty(false);


        return view;
    }

}
