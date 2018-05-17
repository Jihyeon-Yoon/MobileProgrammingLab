package com.example.yjh.lab0502;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    Button button;
    TextView textViewNum;
    TextView textViewResult;
    int inputValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        button = findViewById(R.id.button);
        textViewNum = findViewById(R.id.textViewNum);
        textViewResult = findViewById(R.id.textViewResult);

        //when button is clicked, store the input value, and start thread
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                inputValue = Integer.parseInt(editText.getText().toString());
                new CountDownTask().execute();
            }
        });
    }

    private class CountDownTask extends AsyncTask<Void, Integer, Void> {

        String factoList="";
        int factoResult = 1;

        //initialize the text views
        @Override
        protected void onPreExecute() {
            textViewNum.setText("");
            textViewResult.setText("= ?");
        }

        //call progressUpdate every 500ms, and calculate factorial
        @Override
        protected Void doInBackground(Void... params) {
            for(int i=inputValue; i>0; i--) {
                factoResult *= i;
                try {
                    Thread.sleep(500);
                    publishProgress(i); //Invokes onProgressUpdate();
                } catch(Exception e) {}
            }
            return null;
        }

        //show a single number contained in factorial
        @Override
        protected void onProgressUpdate(Integer... values) {
            factoList += " " + Integer.toString(values[0].intValue());
            textViewNum.setText(factoList);
            textViewResult.setText("= ?");
        }

        //show the calculated factorial result
        @Override
        protected void onPostExecute(Void aVoid) {
            textViewResult.setText("= "+Integer.toString(factoResult));
        }

    }

}
