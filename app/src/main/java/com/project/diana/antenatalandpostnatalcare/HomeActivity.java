package com.project.diana.antenatalandpostnatalcare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.navigation.NavigationView;
import com.project.diana.antenatalandpostnatalcare.Fragments.AppointmentsFragment;
import com.project.diana.antenatalandpostnatalcare.Fragments.HomeFragment;
import com.project.diana.antenatalandpostnatalcare.Fragments.MyAppintmentsFragment;
import com.project.diana.antenatalandpostnatalcare.Fragments.PrenatalRecomandation;
import com.project.diana.antenatalandpostnatalcare.Fragments.ReportFragment;
import com.project.diana.antenatalandpostnatalcare.Fragments.User_HomeFragment;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeActivity extends AppCompatActivity {
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar=findViewById(R.id.vet_toolbar);
        setSupportActionBar(toolbar);

        drawer= findViewById(R.id.vet_drawer_layout);
        NavigationView navigationView= findViewById(R.id.vet_nav_view);
        View headerView = navigationView.getHeaderView(0);

        TextView user= headerView.findViewById(R.id.nav_header_name);
        TextView phone= headerView.findViewById(R.id.nav_header_phone);
        CircleImageView profile_img= headerView.findViewById(R.id.user_profile_image);

        user.setText(Prevalent.currentOnlineUser.getName());
        phone.setText(Prevalent.currentOnlineUser.getPhone());

        Glide.with(this).load(Prevalent.currentOnlineUser.getImage()).into(profile_img);



        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem Item) {
                switch (Item.getItemId()) {
                    case R.id.vet_nav_home:
                        getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.fragment_container,
                                new HomeFragment()).commit();

                        break;


                    case R.id.vet_nav_make_appointment:

                        getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.fragment_container,
                                new AppointmentsFragment()).commit();

                        break;
                    case R.id.vet_nav_my_testresult:

                        getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.fragment_container,
                                new PrenatalRecomandation()).commit();

                        break;
                    case R.id.vet_nav_my_appointments:

                        getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.fragment_container,
                                new MyAppintmentsFragment()).commit();

                        break;
                    case R.id.vet_nav_my_reports:

                        getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.fragment_container,
                                new ReportFragment()).commit();

                        break;
                    case R.id.vet_nav_signout:

                        Signout();

                        break;

                    case R.id.vet_nav_share:

                        Toast.makeText(HomeActivity.this, "Share this app", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.vet_nav_send:

                        Toast.makeText(HomeActivity.this, "Send this app", Toast.LENGTH_SHORT).show();
                        break;

                }

                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,toolbar,
                R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        if (savedInstanceState== null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.vet_nav_home);}
    }

    public void Signout() {
        startActivity(new Intent(this, LoginActivity.class));
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }}
}