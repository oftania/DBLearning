package com.example.dblearning.quiz;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dblearning.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PraktikumFragment extends Fragment {


    public PraktikumFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_praktikum, container, false);
    }

}
