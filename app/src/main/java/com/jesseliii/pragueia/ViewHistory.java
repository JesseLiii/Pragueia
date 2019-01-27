package com.jesseliii.pragueia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class ViewHistory extends AppCompatActivity {

    private EditText mBus, mDaysRented, mMoneyEarnt, mNotes;

    private Button mBack;

    private FirebaseAuth mAuth;
    private DatabaseReference mDriverDatabase;

    private String userID;
    private String bus;
    private String daysRented;
    private String moneyEarnt;
    private String notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_history);

        mBus = (EditText) findViewById(R.id.bus);
        mDaysRented = (EditText) findViewById(R.id.daysrented);
        mMoneyEarnt = (EditText) findViewById(R.id.money);
        mNotes = (EditText) findViewById(R.id.notes);

        mBack = (Button) findViewById(R.id.back);

        mAuth = FirebaseAuth.getInstance();
        userID = mAuth.getCurrentUser().getUid();
        //Structure of data storage in server - Similar to a tree structure, each user can have branches that are the data
        //it possesses, and above the user are the 2 main branches of customer or renter.
        mDriverDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child("Renters").child(userID).child("History");

        getUserInfo();

        //Saves user information to database - Find SaveUserInformation to learn branches and structure to server

        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewHistory.this, RenterProfile.class);
                startActivity(intent);
                finish();
                return;
            }
        });

        mBack = (Button) findViewById(R.id.back);

        mAuth = FirebaseAuth.getInstance();
        userID = mAuth.getCurrentUser().getUid();
        mDriverDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child("Renters").child(userID).child("Buses").child("bus");

        getUserInfo();

        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewHistory.this, RenterProfile.class);
                startActivity(intent);
                finish();
                return;
            }
        });
    }

    private void getUserInfo(){
        mDriverDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists() && dataSnapshot.getChildrenCount()>0){
                    Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
                    if(map.get("bus")!=null){
                        String busType = map.get("bus").toString();
                        mBus.setText(busType);
                    }
                    if(map.get("daysRented")!=null){
                        String pickUpAdress = map.get("daysRented").toString();
                        mDaysRented.setText(pickUpAdress);
                    }
                    if(map.get("moneyEarnt")!=null){
                        String price = map.get("moneyEarnt").toString();
                        mMoneyEarnt.setText(price);
                    }
                    if(map.get("notes")!=null){
                        String seatNum = map.get("notes").toString();
                        mNotes.setText(seatNum);
                    }

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}
