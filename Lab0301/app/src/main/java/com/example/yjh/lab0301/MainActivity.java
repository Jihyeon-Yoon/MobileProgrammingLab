package com.example.yjh.lab0301;

import android.app.Activity;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    Button mBtn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBtn = (Button) findViewById(R.id.button);

        registerForContextMenu(mBtn);   //register the view to which the context menu should be associated
    }

    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
            menu.setHeaderTitle("Button Menu");
            menu.add(0,1,0, "Red");
            menu.add(0,2,0, "Green");
            menu.add(0,3,0, "Blue");
    }

    //change the text color according to the selected itemId
    public boolean onContextItemSelected (MenuItem item) {
        switch(item.getItemId()) {
            case 1:
                mBtn.setTextColor(Color.RED);
                return true;
            case 2:
                mBtn.setTextColor(Color.GREEN);
                return true;
            case 3:
                mBtn.setTextColor(Color.BLUE);
                return true;
        }
        return true;
    }
}
