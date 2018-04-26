package com.example.yjh.lab0302;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PersonInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // hide title bar
        this.getSupportActionBar().hide();

        setContentView(R.layout.activity_person_info);

        TextView name = findViewById(R.id.textView_name);
        TextView gender = findViewById(R.id.textView_gender);
        TextView reception = findViewById(R.id.textView_reception);

        // get the intent from MainActivity
        Intent intent = getIntent();

        // get the bundle from the intent
        Bundle info = intent.getExtras();

        //get the information from the bundle
        String nameStr = info.getString("name");
        String genderStr = info.getString("gender");
        String receptionStr = info.getString("reception");

        // set textViews information
        name.setText(nameStr);
        gender.setText(genderStr);
        reception.setText(receptionStr);

        // when the back button is clicked
        Button backBtn = (Button)findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
    }
}
