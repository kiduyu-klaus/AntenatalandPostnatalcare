package com.project.diana.antenatalandpostnatalcare.Fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.project.diana.antenatalandpostnatalcare.Application;
import com.project.diana.antenatalandpostnatalcare.Appointment;
import com.project.diana.antenatalandpostnatalcare.BookAppointment;
import com.project.diana.antenatalandpostnatalcare.Doctor;
import com.project.diana.antenatalandpostnatalcare.Prevalent;
import com.project.diana.antenatalandpostnatalcare.R;

/**
 * Created by Kiduyu klaus
 * on 14/10/2020 08:07 2020
 */
public class AppointmentsFragment extends Fragment {
    private DatabaseReference mDatabase;
    private RecyclerView recyclerView;
    ProgressDialog pDialog;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View layout = inflater.inflate(R.layout.fragment_appointment, container, false);

        recyclerView = layout.findViewById(R.id.recyclerview_appointments);



        mDatabase = FirebaseDatabase.getInstance().getReference().child("Doctors");
        mDatabase.keepSynced(true);
        recyclerView = layout.findViewById(R.id.recyclerview_appointments);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        pDialog = new ProgressDialog(getActivity());
        getAppointments();











        return layout;
    }

    private void getAppointments() {
        pDialog.setTitle("Fetching Appointments");
        pDialog.setMessage("Please wait, while we are checking the database.");
        pDialog.setCanceledOnTouchOutside(false);
        pDialog.show();


        FirebaseRecyclerOptions<Doctor> option = new FirebaseRecyclerOptions.Builder<Doctor>()
                .setQuery(mDatabase, Doctor.class)
                .build();

        FirebaseRecyclerAdapter<Doctor, MyViewHolderclass> adapter = new FirebaseRecyclerAdapter<Doctor, MyViewHolderclass>(option) {
            @Override
            protected void onBindViewHolder(@NonNull MyViewHolderclass holder, int position, @NonNull Doctor model) {
                final Doctor consultant = model;
                Glide.with(getActivity()).load(consultant.getImage()).placeholder(R.drawable.profile).into(holder.cover);
                holder.title.setText("Dr. "+consultant.getName());
                holder.location.setText("Located in: Nyeri");
                holder.phone.setText("Doctor's Cell: "+consultant.getPhone());
                holder.date.setText("Joined our System on: "+consultant.getDate());

                holder.bookAp.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent= new Intent(getActivity(), BookAppointment.class);
                        intent.putExtra("consultant",consultant.getName());
                        intent.putExtra("consultant_phone",consultant.getPhone());
                        intent.putExtra("consultant_image",consultant.getImage());
                        getActivity().startActivity(intent);
                    }
                });
                pDialog.dismiss();
            }

            @NonNull
            @Override
            public MyViewHolderclass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new MyViewHolderclass(LayoutInflater.from(getActivity()).inflate(R.layout.item_appointment, parent, false));
            }
        };
        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }
    public class MyViewHolderclass extends RecyclerView.ViewHolder {
        TextView title, location, phone, date;
        Button bookAp;
        ImageView cover;

        public MyViewHolderclass(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.consultant_title);
            location = itemView.findViewById(R.id.consultant_location);
            phone = itemView.findViewById(R.id.consultant_phone);
            date = itemView.findViewById(R.id.consultant_date);
            cover = itemView.findViewById(R.id.consultant_image);

            bookAp = itemView.findViewById(R.id.book_appointment);
        }
    }
}
