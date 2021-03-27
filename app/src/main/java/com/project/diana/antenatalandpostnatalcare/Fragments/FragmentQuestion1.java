package com.project.diana.antenatalandpostnatalcare.Fragments;

import android.app.ProgressDialog;
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
 * on 18/10/2020 01:33 2020
 */
public class FragmentQuestion1 extends Fragment {
    TextView tv;
    Button submitbutton, quitbutton;
    RadioGroup radio_g;
    RadioButton rb1, rb2, rb3, rb4;
    String answer;
    private ProgressDialog loadingBar;
    private DatabaseReference mDatabase, mbook;
    String question1 = "Do you experience bleeding during the pregnancy?";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View layout = inflater.inflate(R.layout.fragment_question1, container, false);


        String name = Prevalent.currentOnlineUser.getName();
        loadingBar = new ProgressDialog(getActivity());

        loadingBar = new ProgressDialog(getActivity());
        submitbutton = (Button) layout.findViewById(R.id.button3);
        quitbutton = (Button) layout.findViewById(R.id.buttonquit);
        tv = (TextView) layout.findViewById(R.id.tvque);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Questions");
        radio_g = (RadioGroup) layout.findViewById(R.id.answersgrp);
        rb1 = (RadioButton) layout.findViewById(R.id.radioButton);
        rb2 = (RadioButton) layout.findViewById(R.id.radioButton2);
        tv.setText(question1);
        rb1.setText("Yes");
        rb2.setText("No");
        submitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //int color = mBackgroundColor.getColor();
                //mLayout.setBackgroundColor(color);

                if (radio_g.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(getActivity(), "Please select one choice", Toast.LENGTH_SHORT).show();
                    return;
                }
                RadioButton uans = (RadioButton) layout.findViewById(radio_g.getCheckedRadioButtonId());
                String ansText = uans.getText().toString();
//                Toast.makeText(getApplicationContext(), ansText, Toast.LENGTH_SHORT).show();
                if (ansText.equals("Yes")) {

                    answer="yes";
                    Toast.makeText(getActivity(), "yes", Toast.LENGTH_SHORT).show();
                } else {
                    answer="No";
                    Toast.makeText(getActivity(), "No", Toast.LENGTH_SHORT).show();
                }
                SaveToDb(answer);
            }
        });

        quitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              /*  Intent intent=new Intent(getApplicationContext(),ResultActivity.class);
                startActivity(intent);

               */
            }
        });

        return layout;
    }

    private void SaveToDb(String answer) {
        final ProgressDialog loadingBar = new ProgressDialog(getActivity());
        loadingBar.setTitle("Loading Page");
        loadingBar.setMessage("Please wait,...");
        loadingBar.setCanceledOnTouchOutside(false);
        loadingBar.show();

        QuestionsModel questionsModel= new QuestionsModel(answer,"","","","");
        final DatabaseReference RootRef;

        RootRef = mDatabase;

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    RootRef.child(Prevalent.currentOnlineUser.getName()).setValue(questionsModel);
                loadingBar.dismiss();
                getActivity().getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.fragment_container,
                        new FragmentQuestion2()).commit();



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
