package com.example.yjh.lab0202;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class NewActivity extends AppCompatActivity {
    TextView textView;
    Button goBtn;
    Button backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        //xml파일에서 id 속성을 준 객체들을 findViewById로 불러옴
        textView = (TextView)findViewById(R.id.printURL);
        goBtn = (Button)findViewById(R.id.goButton);
        backBtn = (Button)findViewById(R.id.backButton);

        //mainActivity에서 받은 인텐트로 url을 TextView로 띄워준다.
        Intent passedIntent = getIntent();
        final String passedURL = passedIntent.getStringExtra("URL");
        textView.setText(passedURL);

        //go 버튼을 눌렀을 때 url이 null이 아니면 웹페이지를 띄우고, null이면 toast message를 띄운다.
        goBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!passedURL.equals("")) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://" + passedURL));
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "주소를 다시 입력해 주세요.", Toast.LENGTH_LONG).show();
                }
            }
        });

        //back버튼을 누르면 toast message를 띄우고 newActivity를 finish() 시킨다.
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "뒤로가기 버튼을 눌렀습니다.", Toast.LENGTH_LONG).show();
                finish();
            }
        });

    }
}
