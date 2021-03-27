package com.project.diana.antenatalandpostnatalcare.DoctorHome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.project.diana.antenatalandpostnatalcare.DoctorHome.Fragments.ApproveAppFragmentDoctor;
import com.project.diana.antenatalandpostnatalcare.DoctorHome.Fragments.ApprovePayFragmentDoctor;
import com.project.diana.antenatalandpostnatalcare.DoctorHome.Fragments.HomeFragmentDoctor;
import com.project.diana.antenatalandpostnatalcare.DoctorHome.Fragments.NotificationFragmentDoctor;
import com.project.diana.antenatalandpostnatalcare.DoctorHome.Fragments.UpdatesFragmentDoctor;
import com.project.diana.antenatalandpostnatalcare.LoginActivity;
import com.project.diana.antenatalandpostnatalcare.Prevalent;
import com.project.diana.antenatalandpostnatalcare.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class DoctorHome extends AppCompatActivity {
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_home);


        Toolbar toolbar = findViewById(R.id.doctor_toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.doctor_drawer_layout);
        NavigationView navigationView = findViewById(R.id.doctor_nav_view);
        View headerView = navigationView.getHeaderView(0);

        TextView user = headerView.findViewById(R.id.doctor_header_fullname);
        TextView phone = headerView.findViewById(R.id.doctor_header_email);
        CircleImageView profile_img = headerView.findViewById(R.id.user_profile_image);

        if (Prevalent.currentOnlineUser.getName().isEmpty()) {
            user.setText("Username");
        } else {
            user.setText(Prevalent.currentOnlineUser.getName());
        }

        if (Prevalent.currentOnlineUser.getPhone().isEmpty()) {
            phone.setText("phone");

        } else {
            phone.setText(Prevalent.currentOnlineUser.getPhone());
        }
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem Item) {
                switch (Item.getItemId()) {
                    case R.id.doc_nav_home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_doctor,
                                new HomeFragmentDoctor()).commit();

                        break;
                    case R.id.doc_nav_approveApp:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_doctor,
                                new ApproveAppFragmentDoctor()).commit();

                        break;
                    case R.id.doc_nav_approvePay:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_doctor,
                                new ApprovePayFragmentDoctor()).commit();

                        break;
                    case R.id.doc_nav_updates:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_doctor,
                                new UpdatesFragmentDoctor()).commit();

                        break;
                    case R.id.doc_nav_sendnotifications:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_doctor,
                                new NotificationFragmentDoctor()).commit();

                        break;
                    case R.id.doc_nav_signout:
                        startActivity(new Intent(DoctorHome.this, LoginActivity.class));

                        break;


                }

                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_doctor,
                    new HomeFragmentDoctor()).commit();
        }
    }

}