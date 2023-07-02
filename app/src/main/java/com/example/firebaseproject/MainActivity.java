package com.example.firebaseproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    EditText editCode, editName, editUnit, editAmount, editPrice, editPackaging, editType, editExpired;
    DatabaseReference dbr;
    Button saveButton;
    ModelBarang modelBarang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editCode = findViewById(R.id.itemCode);
        editName = findViewById(R.id.itemName);
        editUnit = findViewById(R.id.itemUnit);
        editAmount = findViewById(R.id.itemAmount);
        editPrice = findViewById(R.id.itemPrice);
        editPackaging = findViewById(R.id.itemPackaging);
        editType = findViewById(R.id.itemType);
        editExpired = findViewById(R.id.itemExpired);

        saveButton = findViewById(R.id.saveButton);

        modelBarang= new ModelBarang();
        //terkoneksi dengan tabel barang
        dbr= FirebaseDatabase.getInstance().getReference().child("barang");

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editCode.getText().toString().equals("")){
                    Toast.makeText(MainActivity.this, "Kode tidak boleh kosong!", Toast.LENGTH_SHORT).show();
                } else if (editName.getText().toString().equals("")) {
                    Toast.makeText(MainActivity.this, "Nama tidak boleh kosong!", Toast.LENGTH_SHORT).show();
                } else if (editUnit.getText().toString().equals("")) {
                    Toast.makeText(MainActivity.this, "Satuan tidak boleh kosong!", Toast.LENGTH_SHORT).show();
                } else if (editPrice.getText().toString().equals("")) {
                    Toast.makeText(MainActivity.this, "Harga tidak boleh kosong!", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this, "Data tersimpan!", Toast.LENGTH_SHORT).show();
                    modelBarang.setItemCode(editCode.getText().toString());
                    modelBarang.setItemName(editName.getText().toString());
                    modelBarang.setItemUnit(editUnit.getText().toString());
                    modelBarang.setItemAmount(editAmount.getText().toString());
                    modelBarang.setItemPrice(Integer.parseInt(editPrice.getText().toString()));
                    modelBarang.setItemExpired(editExpired.getText().toString());
                    modelBarang.setItemPackaging(editPackaging.getText().toString());
                    modelBarang.setItemType(editType.getText().toString());

                    dbr.push().setValue(modelBarang);
                }
            }
        });
    }
}