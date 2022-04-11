package com.example.kurs;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    Activity activity;
    Context context;
    ArrayList full_name, car_model, number, status, car_id, vin;


    CustomAdapter(Activity activity, Context context, ArrayList car_id, ArrayList full_name, ArrayList car_model,
                                                    ArrayList number, ArrayList vin, ArrayList status)
    {
        this.activity = activity;
        this.vin = vin;
        this.context = context;
        this.car_id = car_id;
        this.full_name = full_name;
        this.car_model = car_model;
        this.number = number;

        this.status = status;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.txt_car_id.setText(String.valueOf(car_id.get(position)));
        holder.txt_full_name.setText(String.valueOf(full_name.get(position)));
        holder.txt_car_model.setText(String.valueOf(car_model.get(position)));
        holder.txt_number.setText(String.valueOf(number.get(position)));
        holder.txt_status.setText(String.valueOf(status.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id", String.valueOf(car_id.get(position)));
                intent.putExtra("full_name", String.valueOf(full_name.get(position)));
                intent.putExtra("car_model", String.valueOf(car_model.get(position)));
                intent.putExtra("number", String.valueOf(number.get(position)));
                intent.putExtra("vin", String.valueOf(vin.get(position)));
                intent.putExtra("status", String.valueOf(status.get(position)));

                activity.startActivityForResult(intent,1);
            }
        });
    }


    @Override
    public int getItemCount() {
        return car_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txt_car_id, txt_full_name, txt_car_model, txt_number, txt_status;
        LinearLayout mainLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_car_id = itemView.findViewById(R.id.txt_car_id);
            txt_full_name = itemView.findViewById(R.id.txt_full_name);
            txt_car_model = itemView.findViewById(R.id.txt_car_model);
            txt_number = itemView.findViewById(R.id.txt_number);
            txt_status = itemView.findViewById(R.id.txt_status);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
