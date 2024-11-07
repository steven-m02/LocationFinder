package com.example.locationfinder;

import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    LocationFinderDatabase locationDB;
    EditText editAddress, editLatitude, editLongitude, editTextId;
    Button ButtonAddData, ButtonviewUpdate, ButtonDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        locationDB = new LocationFinderDatabase(this);
//link to XML
        editAddress = findViewById(R.id.editText_address);
        editLatitude = findViewById(R.id.editText_latitude);
        editLongitude = findViewById(R.id.editText_longitude);
        editTextId = findViewById(R.id.editText_id);

        ButtonAddData = findViewById(R.id.button_add);
        ButtonviewUpdate = findViewById(R.id.button_update);
        ButtonDelete = findViewById(R.id.button_delete);

        AddData();
        UpdateData();
        DeleteData();
    }

    // Add data 
    public void AddData() {
        ButtonAddData.setOnClickListener(
                v -> {
                    String address = editAddress.getText().toString();
                    String latitude = editLatitude.getText().toString();
                    String longitude = editLongitude.getText().toString();

                    if (address.isEmpty() || latitude.isEmpty() || longitude.isEmpty()) {
                        Toast.makeText(MainActivity.this, "All fields must be filled", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    try {
                        double lat = Double.parseDouble(latitude);
                        double lon = Double.parseDouble(longitude);

                        boolean isInserted = locationDB.insertData(address, lat, lon);
                        if (isInserted)
                            Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this, "Data not Inserted", Toast.LENGTH_LONG).show();
                    } catch (NumberFormatException e) {
                        Toast.makeText(MainActivity.this, "Latitude and Longitude must be numbers", Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }

    // Update data 
    public void UpdateData() {
        ButtonviewUpdate.setOnClickListener(
                v -> {
                    String id = editTextId.getText().toString();
                    String address = editAddress.getText().toString();
                    String latitude = editLatitude.getText().toString();
                    String longitude = editLongitude.getText().toString();

                    if (id.isEmpty() || address.isEmpty() || latitude.isEmpty() || longitude.isEmpty()) {
                        Toast.makeText(MainActivity.this, "All fields must be filled", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    //parse values
                    try {
                        double lat = Double.parseDouble(latitude);
                        double lon = Double.parseDouble(longitude);

                        boolean isUpdate = locationDB.updateData(id, address, lat, lon);
                        if (isUpdate)
                            Toast.makeText(MainActivity.this, "Data Updated", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this, "Data not Updated", Toast.LENGTH_LONG).show();
                    } catch (NumberFormatException e) {
                        Toast.makeText(MainActivity.this, "Latitude and Longitude must be numbers", Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }

    // Delete data
    public void DeleteData() {
        ButtonDelete.setOnClickListener(
                v -> {
                    String id = editTextId.getText().toString();
                    if (id.isEmpty()) {
                        Toast.makeText(MainActivity.this, "Please enter ID to delete", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    Integer deletedRows = locationDB.deleteData(id);
                    if (deletedRows > 0)
                        Toast.makeText(MainActivity.this, "Data Deleted", Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(MainActivity.this, "Data not Deleted", Toast.LENGTH_LONG).show();
                }
        );
    }

   
}
