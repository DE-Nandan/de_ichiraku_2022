package com.example.de_ichiraku.UserActivity;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.de_ichiraku.R;

public class CompleteFinalOrderActivity extends AppCompatActivity {

    private EditText nameEditText,phoneEditText,addressEditText,cityEditText;
    private Button confirmOrderBtn;
    private String totalAmount = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_final_order);
        totalAmount = getIntent().getStringExtra("Total Price");
        Toast.makeText(CompleteFinalOrderActivity.this, totalAmount, Toast.LENGTH_SHORT).show();
        confirmOrderBtn = (Button) findViewById(R.id.confirem_final_order_btn);
        nameEditText = (EditText) findViewById(R.id.shipment_name);
        phoneEditText = (EditText) findViewById(R.id.shipment_phone_number);
        addressEditText = (EditText) findViewById(R.id.shipment_address);
        cityEditText = (EditText) findViewById(R.id.shipment_city);
    }
}