package com.jesseliii.pragueia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class AddBus extends AppCompatActivity {

    private EditText mBusType, mSeatNum, mPricePDay, mPickupAdress, mRules, mIsOut;
    private Button mAddBus, mBack;

    private FirebaseAuth mAuth;
    private DatabaseReference mDriverDatabase, mBusNumDatabase;
    private String userID;
    //private int busNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bus);


        mAuth = FirebaseAuth.getInstance();
        userID = mAuth.getCurrentUser().getUid();
        mDriverDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child("Renters").child(userID).child("Buses").child("bus");
        //mBusNumDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child("Renters").child(userID).child("Buses");


        mBusType = (EditText) findViewById(R.id.bustype);
        mSeatNum = (EditText) findViewById(R.id.seatnum);
        mPricePDay = (EditText) findViewById(R.id.priceperday);
        mPickupAdress= (EditText) findViewById(R.id.pickupadress);
        mRules = (EditText) findViewById(R.id.rules);
        mIsOut = (EditText) findViewById(R.id.isout);

        mAddBus = (Button) findViewById(R.id.addbus);
        mBack = (Button) findViewById(R.id.back);

        //getUserInfo();


        mAddBus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUserInformation();
                }
        });

        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddBus.this, RenterProfile.class);
                startActivity(intent);
                finish();
                return;
            }
        });

    }


    private void saveUserInformation() {
        int seatNum = Integer.parseInt(mSeatNum.getText().toString());
        int pricepday = Integer.parseInt(mPricePDay.getText().toString());
        String[] rules = new String[10];
        String rulestext = mRules.getText().toString();
        int rulesCount = 0;
        int charCount = 0;
        int ruleStart = 0;
        for (int i = 0; i < 10; i++) {
            if (charCount >= rulestext.length()) {
                break;
            }
            while (Character.isLowerCase(rulestext.charAt(charCount))) {
                charCount++;
            }
            String rule = rulestext.substring(ruleStart, charCount);
            ruleStart = charCount;
            rules[i] = rule;
        }

        String busType = mBusType.getText().toString();
        String pickUpAdress = mPickupAdress.getText().toString();
        String isOut = mIsOut.getText().toString();

        Map userInfo = new HashMap();
        userInfo.put("BusType", busType);
        userInfo.put("seatNum", seatNum);
        userInfo.put("pricePDay", pricepday);
        userInfo.put("pickUpAdress", pickUpAdress);
        userInfo.put("IsOut", isOut);
        userInfo.put("Rule1", rules[0]);
        userInfo.put("Rule2", rules[1]);
        userInfo.put("Rule3", rules[2]);
        userInfo.put("Rule4", rules[3]);
        userInfo.put("Rule5", rules[4]);
        userInfo.put("Rule6", rules[5]);
        userInfo.put("Rule7", rules[6]);
        userInfo.put("Rule8", rules[7]);
        userInfo.put("Rule9", rules[8]);
        userInfo.put("Rule10", rules[9]);


        mDriverDatabase.updateChildren(userInfo);

    }
}
