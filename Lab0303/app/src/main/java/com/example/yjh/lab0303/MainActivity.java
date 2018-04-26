package com.example.yjh.lab0303;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public Button btn_frag1;
    public Button btn_frag2;
    FirstFragment firstFragment;
    SecondFragment secondFragment;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        setListener();
    }

    // initialize widgets
    public void init() {
        btn_frag1 = findViewById(R.id.btn_frag1);
        btn_frag2 = findViewById(R.id.btn_frag2);

        firstFragment = new FirstFragment();
        secondFragment = new SecondFragment();

        textView = findViewById(R.id.textView);
    }

    public void setListener() {
        btn_frag1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                // replace fragment
                transaction.replace(R.id.frameLayout, firstFragment);
                // commit the transaction
                transaction.commit();
            }
        });

        btn_frag2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                // replace fragment
                transaction.replace(R.id.frameLayout, secondFragment);
                // commit the transaction
                transaction.commit();

                // clear TextView
                textView.setText("");
            }
        });
    }

}
