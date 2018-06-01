package com.example.yjh.lab0602;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText studentNum;
    private EditText name;
    private Button readBtn;
    private Button writeBtn;
    private Button resetBtn;
    private String nameTemp;
    private String stuNumTemp;

    SharedPreferences sh_Pref;
    SharedPreferences.Editor toEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        studentNum = findViewById(R.id.studentNum);
        name = findViewById(R.id.name);
        readBtn = findViewById(R.id.readBtn);
        writeBtn = findViewById(R.id.writeBtn);
        resetBtn = findViewById(R.id.resetBtn);

        //reset all sectors, and show message
        resetBtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                studentNum.setText("...");
                name.setText("...");
                Toast.makeText(getApplicationContext(), "reset all", Toast.LENGTH_LONG).show();
            }
        });

        //store data by using shared preference, and show message
        writeBtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                //get data from user
                nameTemp = name.getText().toString();
                stuNumTemp = studentNum.getText().toString();
                //store data
                sharedPreferences();
                //show message
                Toast.makeText(getApplicationContext(), "infomation is saved", Toast.LENGTH_LONG).show();
            }
        });

        //read data by using shared preference, and show message
        readBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //read data by using shared preference
                applySharedPreference();
                //show message
                Toast.makeText(getApplicationContext(), "reading infomation", Toast.LENGTH_LONG).show();
            }
        });
    }

    //store data
    public void sharedPreferences() {
        sh_Pref = getSharedPreferences("studentInfo", MODE_PRIVATE);
        toEdit = sh_Pref.edit();
        toEdit.putString("studentNum", stuNumTemp);
        toEdit.putString("name", nameTemp);
        toEdit.commit();
    }

    //read data
    public void applySharedPreference() {
        sh_Pref = getSharedPreferences("studentInfo", MODE_PRIVATE);
        if(sh_Pref != null && sh_Pref.contains("studentNum")) {
            String temp = sh_Pref.getString("studentNum", "noNum");
            studentNum.setText(temp);
        }
        if(sh_Pref != null && sh_Pref.contains("name")) {
            String temp = sh_Pref.getString("name", "noName");
            name.setText(temp);
        }
    }
}
