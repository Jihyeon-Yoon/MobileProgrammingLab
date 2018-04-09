package com.example.yjh.lab0201;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class NewActivity extends AppCompatActivity {
    Button close;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        //mainActivity에서 받은 인텐드로 name과 age를 toast message로 띄워줌
        Intent passedIntent=getIntent();
        if(passedIntent!=null){
            String loginName=passedIntent.getStringExtra("loginName");
            String loginAge=passedIntent.getStringExtra("loginAge");
            Toast.makeText(getApplicationContext(),"Student info: "+loginName+", "+loginAge,Toast.LENGTH_LONG).show();
        }
        //닫기 버튼을 누르면 newActivity를 finish()시킴
        close=(Button) findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}

