package com.example.studentdbapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddstudentActivity extends AppCompatActivity {
    EditText e1,e2,e3,e4;
    AppCompatButton b1,b2;
    String getName,getAdNo,getRollNo,getCollege;
    DatabaseHelper dbhelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addstudent);
        dbhelper=new DatabaseHelper(this);
        dbhelper.getWritableDatabase();
        e1=(EditText)findViewById(R.id.name);
        e2=(EditText)findViewById(R.id.adno);
        e3=(EditText)findViewById(R.id.rollno);
        e4=(EditText)findViewById(R.id.college);
        b1=(AppCompatButton)findViewById(R.id.submit);
        b2=(AppCompatButton)findViewById(R.id.backtomenu);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getName=e1.getText().toString();
                getAdNo=e2.getText().toString();
                getRollNo=e3.getText().toString();
                getCollege=e4.getText().toString();
                boolean result=dbhelper.insertData(getName,getAdNo,getRollNo,getCollege);
                if(result==true){
                    e1.setText("");
                    e2.setText("");
                    e3.setText("");
                    e4.setText("");
                    Toast.makeText(getApplicationContext(),"Sucessfully inserted",Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(getApplicationContext(),"failed to insert",Toast.LENGTH_LONG).show();
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