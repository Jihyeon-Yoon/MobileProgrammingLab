package com.example.yjh.lab0603;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText editName;
    private EditText editNum;
    private Button writeBtn;
    private Button deleteBtn;
    private String name;
    private String num;

    String[] studentInfo;
    ListView listView;
    ArrayAdapter<String> adapter;

    SQLiteDatabase db;
    MySQLiteOpenHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editName = findViewById(R.id.editName);
        editNum = findViewById(R.id.editNum);
        writeBtn = findViewById(R.id.writeBtn);
        deleteBtn = findViewById(R.id.deleteBtn);

        //create open helper
        helper = new MySQLiteOpenHelper(MainActivity.this, "student.db", null, 1);

        //connect list view
        listView = findViewById(R.id.listView);

        //when write button is clicked
        writeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get data from user
                name = editName.getText().toString();
                num = editNum.getText().toString();
                //if all sectors are not written, show message
                if(name.equalsIgnoreCase("") || num.equals("")) {
                    Toast.makeText(getApplicationContext(), "모든 항목을 입력해주세요.", Toast.LENGTH_LONG).show();
                } else { //if all sectors are filled, store data in DB, and show list by using list view
                    //reset editText
                    editName.setText("");
                    editNum.setText("");
                    //insert data in DB
                    insert(name, num);
                    //show data list by using list view
                    showList();
                }
            }
        });

        //when delete button is clicked
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get data from user
                name = editName.getText().toString();
                //if name is not written, show message
                if(name.equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), "이름을 입력해주세요.", Toast.LENGTH_LONG).show();
                } else { //if name is written, delete data from DB, and show updating data list by using list view
                    //reset editText
                    editName.setText("");
                    editNum.setText("");
                    //delete data from DB
                    delete(name);
                    //show data list by using list view
                    showList();
                }
            }
        });
    }

    private void showList() {
        //get all data from DB, and store it in studentInfo
        select();
        //create adapter
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, studentInfo);
        //show data list
        listView.setAdapter(adapter);
    }

    //insert data in DB
    public void insert(String name, String number) {
        db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("name", name);
        values.put("number", number);
        db.insert("student", null, values);
    }

    //delete data from DB
    public void delete(String name) {
        db = helper.getWritableDatabase();
        db.delete("student", "name=?", new String[]{name});
        Log.i("db1", name + "정상적으로 삭제 되었습니다.");
    }

    //get all data from DB, and store it in studentInfo
    public void select() {
        db = helper.getReadableDatabase();
        Cursor c = db.query("student", null, null, null, null, null, null);

        studentInfo = new String[c.getCount()];
        int count = 0;

        while(c.moveToNext()) {
            studentInfo[count] = c.getString(c.getColumnIndex("name"))
                    + " " + c.getString(c.getColumnIndex("number"));
            count++;
        }
        c.close();
    }

    //to make and use DB
    public class MySQLiteOpenHelper extends SQLiteOpenHelper {

        public MySQLiteOpenHelper(Context context, String name,
                                  SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String sql = "create table student (" +
                    "name string, " +
                    "number string);";
            db.execSQL(sql);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            String sql = "drop table if exists student";
            db.execSQL(sql);
            onCreate(db);
        }
    }
}
