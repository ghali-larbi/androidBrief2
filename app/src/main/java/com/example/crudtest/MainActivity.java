package com.example.crudtest;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.nio.Buffer;

public class MainActivity extends AppCompatActivity {
    EditText firstName,lastName,adresse;
    DBhelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firstName=findViewById(R.id.FirstName);
        lastName=findViewById(R.id.LastName);
        adresse=findViewById(R.id.Adresse);
        db=new DBhelper(this);
    }
    public void Add(View view) {
        String firstNameTXT=firstName.getText().toString();
        String lastNameTXT=lastName.getText().toString();
        String adresseTXT=adresse.getText().toString();
        boolean checkingInsert=db.insertUser(firstNameTXT,lastNameTXT,adresseTXT);
        if(checkingInsert==true){
            Toast.makeText(getApplicationContext(),
                    "insertDone",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(getApplicationContext(),
                    "error",Toast.LENGTH_SHORT).show();
        }
    }

    public void Update(View view) {
        String firstNameTXT=firstName.getText().toString();
        String lastNameTXT=lastName.getText().toString();
        String adresseTXT=adresse.getText().toString();
        boolean checkingUpdate=db.updateUser(firstNameTXT,lastNameTXT,adresseTXT);
        if(checkingUpdate==true){
            Toast.makeText(getApplicationContext(),
                    "update",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(getApplicationContext(),
                    "error",Toast.LENGTH_SHORT).show();
        }
    }
    public void Delete(View view) {
        String firstNameTXT=firstName.getText().toString();
        boolean checkingDelete=db.deleteUser(firstNameTXT);
        if(checkingDelete==true){
            Toast.makeText(getApplicationContext(),
                    "delete",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(getApplicationContext(),
                    "error",Toast.LENGTH_SHORT).show();
        }
    }
    public void View(View view) {
        Cursor res=db.getAllUser();
        if(res.getCount()==0){
            Toast.makeText(getApplicationContext(),
                    "No user exists",Toast.LENGTH_SHORT).show();
            return;
        }
        StringBuffer buffer=new StringBuffer();
        while (res.moveToNext()){
            buffer.append("firstName : "+res.getString(0)+"\n");
            buffer.append("LastName : "+res.getString(1)+"\n");
            buffer.append("adresse : "+res.getString(2)+"\n\n");
        }
        AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
        builder.setCancelable(true);
        builder.setTitle("user informations");
        builder.setMessage(buffer.toString());
        builder.show();


    }

}