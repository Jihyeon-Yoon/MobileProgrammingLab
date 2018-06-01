package com.example.yjh.lab0601;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private Button writeBtn;
    private Button readBtn;
    private Button clearBtn;
    private Button finishBtn;
    private String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText=findViewById(R.id.editText);
        writeBtn=findViewById(R.id.writeBtn);
        readBtn=findViewById(R.id.readBtn);
        clearBtn=findViewById(R.id.clearBtn);
        finishBtn=findViewById(R.id.finishBtn);

        path = Environment.getExternalStorageDirectory().getAbsolutePath();
        final File directory = new File(path+"/");
        directory.mkdirs();

        //store data in file by using IOstream
        writeBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    File file = new File(directory, "file.txt");
                    OutputStreamWriter outWriter = new OutputStreamWriter(
                            new FileOutputStream(file));
                    outWriter.append(editText.getText());
                    outWriter.close();
                    Toast.makeText(getApplicationContext(),
                            "Done writing SD 'file.txt'", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });

        //read data from file by using IOstream
        readBtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                try {
                    BufferedReader reader = new BufferedReader(
                            new InputStreamReader(
                                    new FileInputStream(
                                            new File(directory, "file.txt"))));
                    String dataRow = "";
                    String buffer="";
                    while((dataRow = reader.readLine()) != null) {
                        buffer += dataRow + "\n";
                    }
                    editText.setText(buffer);
                    reader.close();
                    Toast.makeText(getApplicationContext(),
                            "Done reading SD 'file.txt'", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), e.getMessage(),
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        //reset all sectors
        clearBtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                editText.setText("");
                Toast.makeText(getApplicationContext(),
                        "clear screen", Toast.LENGTH_LONG).show();
            }
        });

        finishBtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                finish();
            }
        });
    }
}
