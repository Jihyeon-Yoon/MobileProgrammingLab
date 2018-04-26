package com.example.yjh.lab0302;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    RadioGroup radioGroupMW;
    RadioButton radM, radW;
    CheckBox checkSms, checkEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        radioGroupMW = findViewById(R.id.radioGroupMW);
        radM = findViewById(R.id.radM);
        radW = findViewById(R.id.radW);
        checkSms = findViewById(R.id.check_sms);
        checkEmail = findViewById(R.id.check_email);

        Button registerBtn = findViewById(R.id.button_register);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editText.getText().toString();
                String gender = "", reception = "";

                // radio button
                int radioId = radioGroupMW.getCheckedRadioButtonId();
                if(radM.getId() == radioId) {
                    gender = "남";
                }
                else if(radW.getId() == radioId) {
                    gender = "여";
                }

                // check box
                if(checkSms.isChecked() && checkEmail.isChecked()) {
                    reception = "SMS & e-mail";
                }
                else if(checkSms.isChecked()) {
                    reception = "SMS";
                }
                else if(checkEmail.isChecked()) {
                    reception = "e-mail";
                }

                // make bundle
                Bundle info = new Bundle();
                info.putString("name", name);
                info.putString("gender", gender);
                info.putString("reception", reception);

                // make intent and put the bundle into intent
                Intent intent = new Intent(MainActivity.this, PersonInfoActivity.class);
                intent.putExtras(info);
                startActivity(intent);

                // clear editText, radio buttons, check boxes
                editText.setText("");
                radioGroupMW.clearCheck();
                checkSms.setChecked(false);
                checkEmail.setChecked(false);
            }
        });
    }
}
