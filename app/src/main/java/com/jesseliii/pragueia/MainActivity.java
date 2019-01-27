package com.jesseliii.pragueia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button mRenter, mCustomer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Home Page, this directs to the XML file that shows the GUI and is what the user sees.
        setContentView(R.layout.activity_main);

        //Connecting the buttons from the xml gui file to java
        mRenter = (Button) findViewById(R.id.renter);
        mCustomer = (Button) findViewById(R.id.customer);

        //This format is how you make buttons do something. It directs it to "RenterLogin Activity, follow this
        //trail to learn the basics of Android Studio.
        mRenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RenterLoginActivity.class);
                startActivity(intent);
                finish();
                return;
            }
        });

        mCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CustomerLoginActivity.class);
                startActivity(intent);
                finish();
                return;
            }
        });
    }
}
