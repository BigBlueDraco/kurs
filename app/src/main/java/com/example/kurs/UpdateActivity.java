package com.example.kurs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.kurs.db.MyDbManager;

public class UpdateActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    EditText ed_full_name, ed_car_model,ed_number, ed_vin;
    Spinner sp_status;
    Button btn_update, btn_delete;
    String full_name, car_model, number, vin, status, spText, car_id;
    MyDbManager db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        ed_full_name = findViewById(R.id.edT_Full_Name_Update);
        ed_car_model = findViewById(R.id.edT_Car_model_Update);
        ed_number = findViewById(R.id.edT_Number_Update);
        ed_vin = findViewById(R.id.edT_Vin_Update);
        ed_full_name.setText("");

        sp_status = findViewById(R.id.spStatus_Update);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.status, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_status.setAdapter(adapter);
        sp_status.setOnItemSelectedListener(this);


        btn_update = findViewById(R.id.btn_update);
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.updateData(car_id,
                        ed_full_name.getText().toString(),
                        ed_car_model.getText().toString(),
                        ed_number.getText().toString(),
                        ed_vin.getText().toString(), spText);
            }
        });
        btn_delete = findViewById(R.id.btn_delete);
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                conflictDialog();
            }
        });
        getAndSetIntentData();
        db = new MyDbManager(UpdateActivity.this);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setTitle(full_name);
        }


    }
    void getAndSetIntentData(){
        if(getIntent().hasExtra("full_name") &&
                getIntent().hasExtra("car_model") &&
                getIntent().hasExtra("number") &&
                getIntent().hasExtra("vin") &&
                getIntent().hasExtra("status") && getIntent().hasExtra("id"))
        {
            //Getting data
            car_id = getIntent().getStringExtra("id");
            full_name = getIntent().getStringExtra("full_name");
            car_model = getIntent().getStringExtra("car_model");
            number = getIntent().getStringExtra("number");
            vin = getIntent().getStringExtra("vin");
            status = getIntent().getStringExtra("status");

            //Setting data
            ed_full_name.setText(full_name);
            ed_car_model.setText(car_model);
            ed_number.setText(number);
            ed_vin.setText(vin);
        }
        else{
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }
}
    public void onItemSelected(@NonNull AdapterView<?> adapterView, View view, int i, long l)
    {
        spText = adapterView.getItemAtPosition(i).toString();
    }

    public void onNothingSelected(AdapterView<?> adapterView) {

    }
    void conflictDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete" + full_name+" ?");
        builder.setMessage("Are you sure you want to delete "+full_name+" ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                db.deleteOneRow(car_id);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }

}