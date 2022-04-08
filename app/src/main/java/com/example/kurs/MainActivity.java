package com.example.kurs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.kurs.db.MyDbHelper;
import com.example.kurs.db.MyDbManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    FloatingActionButton add_button;
    MyDbManager db;
    ArrayList<String> id, full_name, car_model, number, vin, status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db =new MyDbManager(this);

        recyclerView = findViewById(R.id.recyclerView);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });
        id = new ArrayList<>();
        full_name = new ArrayList<>();
        car_model = new ArrayList<>();
        number = new ArrayList<>();
        vin = new ArrayList<>();
        status = new ArrayList<>();
    }

    void storeDataInArray(){
        Cursor cursor = db.readAllDAta();
        if(cursor.getCount()== 0){
            Toast.makeText(this,"No data", Toast.LENGTH_SHORT).show();
        }
        else{
            while(cursor.moveToNext()){
                id.add(cursor.getString(0) );
                full_name.add(cursor.getString(1) );
                car_model.add(cursor.getString(2) );
                number.add(cursor.getString(3) );
                vin.add(cursor.getString(4) );
                status.add(cursor.getString(5) );
            }
        }
        cursor.close();
    }
}