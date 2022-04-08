package com.example.kurs.db;

public class MyConstants {
    public static final String TABLE_NAME = "my_table";
    public static final String _ID = "id";
    public static final String FULL_NAME = "full_mane";
    public static final String CAR_MODEL = "car_model";
    public static final String NUMBER = "number";
    public static final String VIN = "vin";
    public static final String STATUS = "status";
    public static final String DB_NAME = "my_db.db";
    public static final int DB_VER = 2;
    public static final String CREATE_TABLE = "CREATE TABLE "+
            TABLE_NAME  +" (" + _ID + " INTEGER PRIMARY KEY,"
            + FULL_NAME +" TEXT,"
            + CAR_MODEL +" TEXT,"
            + NUMBER +" TEXT,"
            + VIN +" TEXT,"
            + STATUS +" TEXT)";
    public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;



}
