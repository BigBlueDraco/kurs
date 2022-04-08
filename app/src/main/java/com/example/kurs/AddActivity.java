package com.example.kurs;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextPaint;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.kurs.db.MyDbManager;

import org.w3c.dom.Text;

public class AddActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Button add_button;
    EditText full_name, car_model, number, vin;
    MyDbManager myDbManager;
    String  spText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        Spinner spinner = findViewById(R.id.spStatus);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.status, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        full_name = findViewById(R.id.edT_Full_Name);
        car_model = findViewById(R.id.edT_Car_model);
        number = findViewById(R.id.edT_Number);
        vin = findViewById(R.id.edT_Vin);

        add_button = findViewById(R.id.btn_add);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDbManager = new MyDbManager(AddActivity.this);
                myDbManager.openDb();
                myDbManager.insertToDb(full_name.getText().toString(), car_model.getText().toString(), number.getText().toString(), vin.getText().toString(), spText );
                full_name.setText("");
                car_model.setText("");
                number.setText("");
                vin.setText("");
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
    {
        spText = adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}