package com.project.diana.antenatalandpostnatalcare.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.project.diana.antenatalandpostnatalcare.Prevalent;
import com.project.diana.antenatalandpostnatalcare.R;

/**
 * Created by Kiduyu klaus
 * on 18/10/2020 01:33 2020
 */
public class PrenatalRecomandation extends Fragment {
    TextView q1a,q1r,q2a,q2r,q3a,q3r,q4a,q4r,q5a,q5r;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View layout = inflater.inflate(R.layout.fragment_prenatalrecomendadtion, container, false);


        q1a=layout.findViewById(R.id.q1answer);
        q1r=layout.findViewById(R.id.q1recomendadtion);
        q2a=layout.findViewById(R.id.q2answer);
        q2r=layout.findViewById(R.id.q2recomendadtion);
        q3a=layout.findViewById(R.id.q3answer);
        q3r=layout.findViewById(R.id.q3recomendadtion);
        q4a=layout.findViewById(R.id.q4answer);
        q4r=layout.findViewById(R.id.q4recomendadtion);
        q5a=layout.findViewById(R.id.q5answer);
        q5r=layout.findViewById(R.id.q5recomendadtion);


        setData();

        return layout;
    }

    private void setData() {

        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();;


        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child("Questions").child(Prevalent.currentOnlineUser.getName()).exists()) {
                    QuestionsModel questionsModel = dataSnapshot.child("Questions").child(Prevalent.currentOnlineUser.getName()).getValue(QuestionsModel.class);
                    q1a.setText("Respose you gave : "+questionsModel.getQuestion1());
                    if (questionsModel.getQuestion1().equals("yes")){

                        q1r.setText("The Doctor Is Afraid this could be a sign of miscarriage, hormonal bleeding or implantation bleeding hence recommends checkup.");
                    } else {
                        q1r.setText("The Doctor recomends you continue eating well and avoiding heavy work");
                    }
                    q2a.setText("Respose you gave : "+questionsModel.getQuestion2());
                    if (questionsModel.getQuestion2().equals("yes")){

                        q2r.setText("The Doctor recommends immediate check up as this could signal a miscarriage ");
                    } else {
                        q2r.setText("You seem to be progressing well");
                    }

                    q3a.setText("Respose you gave : "+questionsModel.getQuestion3());
                    if (questionsModel.getQuestion3().equals("yes")){

                        q3r.setText("This could be anemia, kidney issues, diabetes which causes intrauterine growth restriction. Recommend close doctor observatione ");
                    } else {
                        q3r.setText("Keep up the good health");
                    }
                    q4a.setText("Respose you gave : "+questionsModel.getQuestion4());
                    if (questionsModel.getQuestion4().equals("yes")){

                        q4r.setText("This could occur due to a couple of reasons namely \n\n1).changes in the baby’s position\n2).placental issues\n3).death of the foetus\n\nthe doctor recommends a ultrasound test to dig out the precise reason behind that.");
                    } else {
                        q4r.setText("Keep it up");
                    }
                    q5a.setText("Respose you gave : "+questionsModel.getQuestion5());

                    if (questionsModel.getQuestion2().equals("yes")){

                        q5r.setText("The Doctor recommends body exercise as this might be due to poor blood flow in early pregnancy , and if it persists consult your doctor");
                    } else {
                        q5r.setText("Overall, You are Healthy");
                    }
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
