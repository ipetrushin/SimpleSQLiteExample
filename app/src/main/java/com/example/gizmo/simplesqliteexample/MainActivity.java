package com.example.gizmo.simplesqliteexample;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText valueText;
    TextView sumText, lastvalueText;
    SQLiteDatabase db;
    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        valueText = (EditText) findViewById(R.id.value);
        sumText = (TextView) findViewById(R.id.sum);
        lastvalueText = (TextView) findViewById(R.id.lastvalue);

        dbHelper = new DBHelper(this);
        db = dbHelper.getWritableDatabase();

    }


    public void onClick(View v)
    {
        int value = Integer.parseInt(valueText.getText().toString());
        if (v.getId() == R.id.add) {

            db.execSQL("INSERT INTO " + dbHelper.TABLE_NAME + " VALUES ("+value+")");
        }
        if (v.getId() == R.id.remove) {

            db.execSQL("DELETE FROM " + dbHelper.TABLE_NAME + " WHERE value = "+value);
        }

        Cursor c = db.rawQuery("SELECT * FROM " + dbHelper.TABLE_NAME, null);
        c.moveToLast();
        value = c.getInt(0);
        lastvalueText.setText("Last value = " + value);

    }
}
