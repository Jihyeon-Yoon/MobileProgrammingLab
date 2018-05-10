package com.example.yjh.lab0401;

import android.os.Bundle;
import android.app.Activity;

public class MainActivity extends Activity {

    //called when the activity is first created
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        PaintBoard board = new PaintBoard(this);
        setContentView(board);
    }
}
