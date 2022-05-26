package com.example.dbtutorial;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText name, contact, dob;
    Button insert, update, delete, view;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        contact = findViewById(R.id.contact);
        dob = findViewById(R.id.dob);

        insert = findViewById(R.id.btnInsert);
        update = findViewById(R.id.btnUpdate);
        delete = findViewById(R.id.btnDelete);
        view = findViewById(R.id.btnView);

        DB = new DBHelper(this);

        insert.setOnClickListener(view -> {
            String nameTxt = name.getText().toString();
            String conatactTxt = contact.getText().toString();
            String dobTxt = dob.getText().toString();

            Boolean dataInserted = DB.insertUserData(nameTxt, conatactTxt, dobTxt);
            if(dataInserted){
                Toast.makeText(MainActivity.this, "data successfully inserted", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(MainActivity.this, "data not inserted", Toast.LENGTH_SHORT).show();

            }
        });

        update.setOnClickListener(view -> {
            String nameTxt = name.getText().toString();
            String conatactTxt = contact.getText().toString();
            String dobTxt = dob.getText().toString();

            Boolean dataUpdated = DB.updateUserData(nameTxt, conatactTxt, dobTxt);
            if(dataUpdated){
                Toast.makeText(MainActivity.this, "Entry updated", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(MainActivity.this, "Entry not updated", Toast.LENGTH_SHORT).show();

            }
        });

        delete.setOnClickListener(view -> {
            String nameTxt = name.getText().toString();

            Boolean dataDeleted = DB.deleteUserData(nameTxt);
            if(dataDeleted){
                Toast.makeText(MainActivity.this, "Entry deleted", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(MainActivity.this, "Entry not deleted", Toast.LENGTH_SHORT).show();
            }
        });

        view.setOnClickListener(view -> {
            Cursor result = DB.getData();
            if(result.getCount() == 0){
                Toast.makeText(MainActivity.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
                return;
            }
            StringBuffer stringBuffer = new StringBuffer();
            while (result.moveToNext()){
                stringBuffer.append("Name :"+ result.getString(0)+"\n");
                stringBuffer.append("Contact :"+ result.getString(1)+"\n");
                stringBuffer.append("Date of Birth :"+ result.getString(2)+"\n\n");

            }

            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setCancelable(true);
            builder.setTitle("Entries available");
            builder.setMessage(stringBuffer.toString());
            builder.show();
        });



    }
}