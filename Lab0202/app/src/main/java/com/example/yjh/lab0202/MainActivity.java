package com.example.yjh.lab0202;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText url;
    Button nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //xml파일에서 id 속성을 준 객체들을 findViewById로 불러옴
        url = (EditText)findViewById(R.id.url);
        nextButton=(Button)findViewById(R.id.nextButton);

        //next 버튼을 눌렀을 때 url을 newActivity로 보내도록 Listener를 만들어줌
        nextButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String URL=url.getText().toString();

                //intent를 통해서 newActivity에 데이터를 넘김
                Intent intent = new Intent(getApplicationContext(),NewActivity.class);
                intent.putExtra("URL",URL);
                startActivity(intent);
            }
        });
    }
}

