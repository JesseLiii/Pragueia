package com.jesseliii.pragueia;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.widget.Toast;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AddHistory extends AppCompatActivity {
    private EditText mBus, mDaysRented, mMoneyEarnt, mNotes;

    private Button mBack, mConfirm;

    private FirebaseAuth mAuth;
    private DatabaseReference mDriverDatabase;

    private String userID;
    private String bus;
    private String daysRented;
    private String moneyEarnt;
    private String notes;

    private Driver driver;
    private ArrayList<ArrayList<RenterHistory>> history = new ArrayList<ArrayList<RenterHistory>>();
    private ArrayList<Driver> drivers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_history);

        mBus = (EditText) findViewById(R.id.bus);
        mDaysRented = (EditText) findViewById(R.id.daysrented);
        mMoneyEarnt = (EditText) findViewById(R.id.money);
        mNotes = (EditText) findViewById(R.id.notes);

        mBack = (Button) findViewById(R.id.back);
        mConfirm = (Button) findViewById(R.id.confirm);

        mAuth = FirebaseAuth.getInstance();
        userID = mAuth.getCurrentUser().getUid();
        //Structure of data storage in server - Similar to a tree structure, each user can have branches that are the data
        //it possesses, and above the user are the 2 main branches of customer or renter.
        mDriverDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child("Renters").child(userID).child("History");

        //getUserInfo();

        //Saves user information to database - Find SaveUserInformation to learn branches and structure to server
        mConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUserInformation();
            }
        });

        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddHistory.this, RenterProfile.class);
                startActivity(intent);
                finish();
                return;
            }
        });
    }

    /**private void getUserInfo(){
        mDriverDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists() && dataSnapshot.getChildrenCount()>0){
                    Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
                    if(map.get("name")!=null){
                        mName = map.get("name").toString();
                        mNameField.setText(mName);
                    }
                    if(map.get("phone")!=null){
                        mPhone = map.get("phone").toString();
                        mPhoneField.setText(mPhone);
                    }
                    if(map.get("car")!=null){
                        mCar = map.get("car").toString();
                        mCarField.setText(mCar);
                    }
                    if(map.get("service")!=null){
                        mService = map.get("service").toString();
                        switch (mService){
                            case"AllDays":
                                mRadioGroup.check(R.id.alldays);
                                break;
                            case"weekends":
                                mRadioGroup.check(R.id.weekends);
                                break;
                            case"weekdays":
                                mRadioGroup.check(R.id.weekdays);
                                break;
                        }
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }*/


    private void saveUserInformation() {
        bus = mBus.getText().toString();
        daysRented = mDaysRented.getText().toString();
        moneyEarnt = mMoneyEarnt.getText().toString();
        notes = mNotes.getText().toString();

        Map userInfo = new HashMap();
        userInfo.put("bus", bus);
        userInfo.put("daysRented", daysRented);
        userInfo.put("moneyEarnt", moneyEarnt);
        userInfo.put("notes", notes);
        mDriverDatabase.updateChildren(userInfo);
    }

    private void addHistory(){
        try{
            int daysrented = Integer.parseInt(mDaysRented.getText().toString());
            int busID= Integer.parseInt(mBus.getText().toString());
            String driverString = mNotes.getText().toString();
            Driver driver = new Driver(driverString);
            for(int i = 0; i < drivers.size(); i++){
                if(driverString.equals(drivers.get(i).getName())){
                    driver = drivers.get(i);
                }
            }
            RenterHistory historyPiece = new RenterHistory(busID, mNotes.getText().toString(), daysrented, driver, mNotes.getText().toString());
            ArrayList<RenterHistory> historyArray = history.get(busID);
            historyArray.add(historyPiece);
            history.set(busID, historyArray);

        }catch(Exception e){
            Toast.makeText(AddHistory.this, "Parameter Error, Please Try Again", Toast.LENGTH_SHORT).show();
        }
    }
}
