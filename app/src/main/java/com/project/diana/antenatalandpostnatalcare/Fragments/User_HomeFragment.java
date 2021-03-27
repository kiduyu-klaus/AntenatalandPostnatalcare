package com.project.diana.antenatalandpostnatalcare.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.project.diana.antenatalandpostnatalcare.Doctor;
import com.project.diana.antenatalandpostnatalcare.Prevalent;
import com.project.diana.antenatalandpostnatalcare.R;

/**
 * Created by Kiduyu klaus
 * on 14/10/2020 07:21 2020
 */
public class User_HomeFragment extends Fragment {
    TextView tv;
    Button submitbutton, quitbutton;
    RadioGroup radio_g;
    RadioButton rb1,rb2,rb3,rb4;
    private ProgressDialog loadingBar;
    private DatabaseReference mDatabase, mbook;
    TextView textView;
    Button start;
    String name;

    int flag=0;
    public static int marks=0,correct=0,wrong=0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View layout = inflater.inflate(R.layout.fragment_home, container, false);

        textView = layout.findViewById(R.id.Home_tv);
       start= layout.findViewById(R.id.buttonstart);


        name = Prevalent.currentOnlineUser.getName();
        mDatabase= FirebaseDatabase.getInstance().getReference().child("Doctors");
        CreateDoctor();
        textView.setText("Hello ,"+name+"\nBefore you continue using the system, please take part in the questionnaire to help the doctors under your condition or your chlid's condition.");
        start.setOnClickListener(v ->{
            getActivity().getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.fragment_container,
                    new FragmentQuestion1()).commit();

        });
        return layout;
    }

    private void CreateDoctor() {
        final ProgressDialog loadingBar = new ProgressDialog(getActivity());
        loadingBar.setTitle("Loading Page");
        loadingBar.setMessage("Please wait,...");
        loadingBar.setCanceledOnTouchOutside(false);
        loadingBar.show();

        final Doctor office= new Doctor("John Main Weru", "0716541458", "25478954", "Nyeri", "https://cdn.pixabay.com/photo/2016/01/19/15/05/doctor-1149149_1280.jpg", "22/05/2020");

        final DatabaseReference RootRef;

        RootRef = mDatabase;

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (!(dataSnapshot.child("John Main Weru").exists())){
                    RootRef.child("John Main Weru").setValue(office);

                    loadingBar.dismiss();
                }else{


                    loadingBar.dismiss();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
/*
    @Override
    public void onStart() {
        super.onStart();

        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();;


        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child("Questions").child(Prevalent.currentOnlineUser.getName()).exists()) {
                    textView.setText("Hello ,"+name+"\nThankyou For participatimg in the simple questionnaire, you can view your recommendation by clicking the button below");

                    start.setText("View Recommendations");

                    start.setOnClickListener(v ->{

                        getActivity().getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.fragment_container,
                                new PrenatalRecomandation()).commit();

                    });
                } else{
                    textView.setText("Hello ,"+name+"\nBefore you continue using the system, please take part in the questionnaire to help the doctors under your condition or your chlid's condition.");
                    start.setOnClickListener(v ->{
                        getActivity().getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.fragment_container,
                                new FragmentQuestion1()).commit();

                    });
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

 */
}
