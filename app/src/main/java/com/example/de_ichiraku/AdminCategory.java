package com.example.de_ichiraku;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class AdminCategory extends AppCompatActivity {

    private ImageView beverages,noodles;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_category);

        beverages = (ImageView) findViewById(R.id.beverages);
        noodles = (ImageView) findViewById(R.id.noodles);

        noodles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCategory.this,AdminAddProduct.class);
                intent.putExtra("category","noodles");
                startActivity(intent);
            }
        });

        beverages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCategory.this,AdminAddProduct.class);
                intent.putExtra("category","beverages");
                startActivity(intent);
            }
        });
    }
}