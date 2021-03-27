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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.project.diana.antenatalandpostnatalcare.Application;
import com.project.diana.antenatalandpostnatalcare.PaymentActivity;
import com.project.diana.antenatalandpostnatalcare.Prevalent;
import com.project.diana.antenatalandpostnatalcare.R;
import com.shashank.sony.fancytoastlib.FancyToast;

/**
 * Created by Kiduyu klaus
 * on 18/10/2020 00:53 2020
 */
public class MyAppintmentsFragment extends Fragment {
    private RecyclerView recyclerView;
    private DatabaseReference mDatabase;
    ProgressDialog pDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View layout = inflater.inflate(R.layout.fragment_myfragment, container, false);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Appointments").child(Prevalent.currentOnlineUser.getName());
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

        Query queries = mDatabase.orderByChild("username").equalTo(Prevalent.currentOnlineUser.getName());
        FirebaseRecyclerOptions<Application> option = new FirebaseRecyclerOptions.Builder<Application>()
                .setQuery(queries, Application.class)
                .build();

        FirebaseRecyclerAdapter<Application, MyViewHolderclass> adapter = new FirebaseRecyclerAdapter<Application, MyViewHolderclass>(option) {
            @Override
            protected void onBindViewHolder(@NonNull MyViewHolderclass holder, int position, @NonNull final Application model) {

                pDialog.dismiss();

                holder.single_application_timeago.setText("less than a day ago");
                holder.single_application_county.setText("Dr. "+model.getConsultant_name());
                holder.single_application_username.setText(model.getUsername());
                holder.single_application_school.setText("Tel : "+model.getConsultant_phone());
                holder.edt_myappications_descri11.setText("An Appointment to Dr."+model.getConsultant_name()+" was set on "+model.getDateValue()+" At "+model.getTime()+"\n\n<---- Please Note --->\n\nOnce The Appointment is Approved, A consoltation fee of Ksh. 200 will be required ");

                holder.status.setText("Appointment Status: "+model.getStatus());

                holder.chat.setOnClickListener(v -> {
                    if (model.getStatus().equals("Pending")) {
                        Toast.makeText(getActivity(), "Appointment Status Must be Approved To Make Payment", Toast.LENGTH_LONG).show();
                    } else{
                        startActivity(new Intent(getActivity(), PaymentActivity.class));
                    }
                });

            }

            @NonNull
            @Override
            public MyViewHolderclass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_appointment_history, parent, false);
                return new MyViewHolderclass(view);
            }
        };
        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }
    public class MyViewHolderclass extends RecyclerView.ViewHolder {
        TextView single_application_username, single_application_county, status, single_application_school, single_application_timeago, edt_myappications_descri11;
        ImageView single_application_image;
        Button chat;

        public MyViewHolderclass(@NonNull View itemView) {
            super(itemView);
            single_application_image = itemView.findViewById(R.id.single_application_user);
            single_application_username = itemView.findViewById(R.id.single_application_username);
            single_application_county = itemView.findViewById(R.id.single_application_county);
            single_application_school = itemView.findViewById(R.id.single_application_school);
            single_application_timeago = itemView.findViewById(R.id.single_application_timeago);
            edt_myappications_descri11 = itemView.findViewById(R.id.edt_myappications_descri11);
            status = itemView.findViewById(R.id.history_pending);
            chat = itemView.findViewById(R.id.chat_appointment);
        }
    }

}
