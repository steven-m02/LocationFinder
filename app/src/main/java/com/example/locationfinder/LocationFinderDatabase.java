package com.example.locationfinder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class LocationFinderDatabase extends SQLiteOpenHelper {

    //creating database name and columns
    public static final String databaseName = "Location.db";
    public static final String TableName = "Location_table";
    public static final String Column1= "id";
    public static final String Column2 = "address";
    public static final String Column3 = "latitude";
    public static final String Column4 = "longitude";

    //initliaze database
    public LocationFinderDatabase(Context context){
        super(context, databaseName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE " + TableName +" (ID INTEGER PRIMARY KEY AUTOINCREMENT, address TEXT, latitude REAL, longitude REAL)");
    }
//for database upgrades
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TableName);
        onCreate(sqLiteDatabase);
    }

    //insert new data
    public boolean insertData(String address, double latitude, double longitude) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Column2, address);
        contentValues.put(Column3, latitude);
        contentValues.put(Column4, longitude);
        long result = db.insert(TableName, null, contentValues);
        return result != -1;
    }

    //update data
    public boolean updateData(String id, String address, double latitude, double longitude) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Column1, id);
        contentValues.put(Column2, address);
        contentValues.put(Column3, latitude);
        contentValues.put(Column4, longitude);
        db.update(TableName, contentValues, "ID = ?", new String[] { id });
        return true;
    }
//remove data
    public Integer deleteData(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TableName, "ID = ?", new String[] { id });
    }
}
