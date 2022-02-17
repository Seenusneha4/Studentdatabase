package com.example.studentdbapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class StudentsearchActivity extends AppCompatActivity {
    EditText e1,e2,e3,e4;
    AppCompatButton b1,b2;
    String getAdNo,getName,getRollNo,getCollege;
    DatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentsearch);
        helper=new DatabaseHelper(this);
        helper.getWritableDatabase();
        e1=(EditText)findViewById(R.id.adno);
        e2=(EditText)findViewById(R.id.name);
        e3=(EditText)findViewById(R.id.rollno);
        e4=(EditText)findViewById(R.id.college);
        b1=(AppCompatButton)findViewById(R.id.search);
        b2=(AppCompatButton)findViewById(R.id.backtomenu);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAdNo=e1.getText().toString();
                getName=e2.getText().toString();
                getRollNo=e3.getText().toString();
                getCollege=e4.getText().toString();
                Cursor c=helper.searchData(getAdNo);
                if(c.getCount()==0)
                {
                  e2.setText("");
                  e3.setText("");
                  e4.setText("");
                    Toast.makeText(getApplicationContext(), "Invalid admission no", Toast.LENGTH_LONG).show();
                }
                else{
                    while (c.moveToNext()){
                        getName=c.getString(1);
                        getRollNo=c.getString(3);
                        getCollege=c.getString(4);
                    }
                    e2.setText(getName);
                    e3.setText(getRollNo);
                    e4.setText(getCollege);
                }
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });
    }
}