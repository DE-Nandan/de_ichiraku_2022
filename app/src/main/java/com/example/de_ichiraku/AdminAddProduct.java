package com.example.de_ichiraku;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class AdminAddProduct extends AppCompatActivity {

    private String categoryName;
    private Button addProd;
    private ImageView prodImg;
    private EditText prodName,prodDes,prodPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_product);

        categoryName = getIntent().getExtras().get("category").toString();

        addProd = (Button) findViewById(R.id.addProd);
        prodImg = (ImageView) findViewById(R.id.prodImage);
        prodName = (EditText) findViewById(R.id.prodName);
        prodDes = (EditText) findViewById(R.id.productDes);
        prodPrice = (EditText) findViewById(R.id.prodPrice);


        //Toast.makeText(this, categoryName, Toast.LENGTH_SHORT).show();
    }
}