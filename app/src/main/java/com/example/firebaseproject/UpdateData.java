package com.example.firebaseproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UpdateData extends AppCompatActivity {
    EditText editCode, editName, editUnit, editAmount, editPrice, editPackaging, editType, editExpired;
    Button updateButton;

    DatabaseReference dbr;
    ModelBarang modelBarang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_data);

        editCode = findViewById(R.id.itemCode);
        editName = findViewById(R.id.itemName);
        editUnit = findViewById(R.id.itemUnit);
        editAmount = findViewById(R.id.itemAmount);
        editPrice = findViewById(R.id.itemPrice);
        editPackaging = findViewById(R.id.itemPackaging);
        editType = findViewById(R.id.itemType);
        editExpired = findViewById(R.id.itemExpired);

        updateButton = findViewById(R.id.updateButton);

        dbr = FirebaseDatabase.getInstance().getReference();
        modelBarang = new ModelBarang();

        //membawa data yg ingin diedit
        Bundle bundle = getIntent().getExtras();
        editCode.setText(bundle.getString("itemCode"));
        editName.setText(bundle.getString("itemName"));
        editUnit.setText(bundle.getString("itemUnit"));
        editAmount.setText(bundle.getString("itemAmount"));
        editPrice.setText(bundle.getString("itemPrice"));
        editPackaging.setText(bundle.getString("itemPackaging"));
        editType.setText(bundle.getString("itemType"));
        editExpired.setText(bundle.getString("itemExpired"));

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String itemKey = bundle.getString("itemKey");

                modelBarang.setItemCode(editCode.getText().toString());
                modelBarang.setItemName(editName.getText().toString());
                modelBarang.setItemUnit(editUnit.getText().toString());
                modelBarang.setItemAmount(editAmount.getText().toString());
                modelBarang.setItemPrice(Integer.parseInt(editPrice.getText().toString()));
                modelBarang.setItemPackaging(editPackaging.getText().toString());
                modelBarang.setItemType(editType.getText().toString());
                modelBarang.setItemExpired(editExpired.getText().toString());

                dbr.child("barang").child(itemKey).setValue(modelBarang).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(UpdateData.this, bundle.getString("itemCode") + " berhasil diupdate!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}