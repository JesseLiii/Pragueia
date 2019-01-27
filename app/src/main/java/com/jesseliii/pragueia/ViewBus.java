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

public class ViewBus extends AppCompatActivity {

    private EditText mBusType, mSeatNum, mPricePDay, mPickupAdress, mRules1, mRules2, mRules3, mRules4, mRules5, mRules6, mRules7, mRules8, mRules9, mRules10;
    private Button mAddBus, mBack;

    private FirebaseAuth mAuth;
    private DatabaseReference mDriverDatabase, mBusNumDatabase;
    private String userID;
    //private int busNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_bus);

        mBusType = (EditText) findViewById(R.id.bustype);
        mSeatNum = (EditText) findViewById(R.id.seatnum);
        mPricePDay = (EditText) findViewById(R.id.priceperday);
        mPickupAdress= (EditText) findViewById(R.id.pickupadress);
        mRules1 = (EditText) findViewById(R.id.rules1);
        mRules2 = (EditText) findViewById(R.id.rules2);
        mRules3 = (EditText) findViewById(R.id.rules3);
        mRules4 = (EditText) findViewById(R.id.rules4);
        mRules5 = (EditText) findViewById(R.id.rules5);
        mRules6 = (EditText) findViewById(R.id.rules6);
        mRules7 = (EditText) findViewById(R.id.rules7);
        mRules8 = (EditText) findViewById(R.id.rules8);
        mRules9 = (EditText) findViewById(R.id.rules9);
        mRules10 = (EditText) findViewById(R.id.rules10);

        mAddBus = (Button) findViewById(R.id.addbus);
        mBack = (Button) findViewById(R.id.back);

        mAuth = FirebaseAuth.getInstance();
        userID = mAuth.getCurrentUser().getUid();
        mDriverDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child("Renters").child(userID).child("Buses").child("bus");

        getUserInfo();

        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewBus.this, RenterProfile.class);
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
                    if(map.get("BusType")!=null){
                        String busType = map.get("BusType").toString();
                        mBusType.setText(busType);
                    }
                    if(map.get("pickUpAdress")!=null){
                        String pickUpAdress = map.get("pickUpAdress").toString();
                        mPickupAdress.setText(pickUpAdress);
                    }
                    if(map.get("pricePDay")!=null){
                        String price = map.get("pricePDay").toString();
                        mPricePDay.setText(price);
                    }
                    if(map.get("seatNum")!=null){
                        String seatNum = map.get("seatNum").toString();
                        mSeatNum.setText(seatNum);
                    }
                    if(map.get("Rule1")!=null){
                        String rule1 = map.get("Rule1").toString();
                        mRules1.setText(rule1);
                    }
                    if(map.get("Rule2")!=null){
                        String rule2 = map.get("Rule2").toString();
                        mRules2.setText(rule2);
                    }
                    if(map.get("Rule3")!=null){
                        String rule3 = map.get("Rule3").toString();
                        mRules3.setText(rule3);
                    }
                    if(map.get("Rule4")!=null){
                        String rule4 = map.get("Rule4").toString();
                        mRules4.setText(rule4);
                    }
                    if(map.get("Rule5")!=null){
                        String rule5 = map.get("Rule5").toString();
                        mRules5.setText(rule5);
                    }
                    if(map.get("Rule6")!=null){
                        String rule6 = map.get("Rule6").toString();
                        mRules6.setText(rule6);
                    }
                    if(map.get("Rule7")!=null){
                        String rule7 = map.get("Rule7").toString();
                        mRules7.setText(rule7);
                    }

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}
