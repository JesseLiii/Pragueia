package com.jesseliii.pragueia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ViewHistoryList extends AppCompatActivity {

    private Button mhistoryOne;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_history_list);

        mhistoryOne = (Button) findViewById(R.id.historyone);

        mhistoryOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewHistoryList.this, ViewHistory.class);
                startActivity(intent);
                finish();
                return;
            }
        });


    }
}
