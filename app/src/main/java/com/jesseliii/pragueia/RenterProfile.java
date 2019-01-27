package com.jesseliii.pragueia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import com.firebase.geofire.GeoFire;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.BufferedInputStream;
import java.sql.Driver;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RenterProfile extends AppCompatActivity {

    private Button mLogout, mProfile, mViewHistory, mAddHistory, mAddBus, mViewBus;
    private Boolean isLoggingOut = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_renter_profile);

        mLogout = (Button) findViewById(R.id.logout);
        mProfile = (Button) findViewById(R.id.profile);

        mViewHistory = (Button) findViewById(R.id.viewhistory);
        mAddHistory = (Button) findViewById(R.id.addhistory);
        mAddBus = (Button) findViewById(R.id.addbus);
        mViewBus = (Button) findViewById(R.id.viewbus);


        mLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isLoggingOut = true;

                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(RenterProfile.this, MainActivity.class);
                startActivity(intent);
                finish();
                return;
            }
        });

        mProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RenterProfile.this, RenterSettings.class);
                startActivity(intent);
                finish();
                return;
            }
        });

        mAddBus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RenterProfile.this, AddBus.class);
                startActivity(intent);
                finish();
                return;
            }
        });

        mViewBus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RenterProfile.this, ViewBus.class);
                startActivity(intent);
                finish();
                return;
            }
        });

        mAddHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RenterProfile.this, AddHistory.class);
                startActivity(intent);
                finish();
                return;
            }
        });


        mViewHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RenterProfile.this, ViewHistoryList.class);
                startActivity(intent);
                finish();
                return;
            }
        });
    }




}

