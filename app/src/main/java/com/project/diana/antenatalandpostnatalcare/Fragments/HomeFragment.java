package com.project.diana.antenatalandpostnatalcare.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.project.diana.antenatalandpostnatalcare.R;

/**
 * Created by Kiduyu klaus
 * on 10/11/2020 02:04 2020
 */
public class HomeFragment extends Fragment {
    private CardView submittv,myformstv,seattv,profile;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        submittv=view.findViewById(R.id.submit);
        myformstv=view.findViewById(R.id.myforms);
        seattv=view.findViewById(R.id.seat);
        profile=view.findViewById(R.id.profile);

        submittv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.fragment_container,
                        new User_HomeFragment()).commit();
            }
        });

        myformstv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.fragment_container,
                        new AppointmentsFragment()).commit();
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.fragment_container,
                        new PrenatalRecomandation()).commit();
            }
        });

        seattv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.fragment_container,
                        new MyAppintmentsFragment()).commit();
            }
        });
        return view;
    }
}
