package com.example.de_ichiraku.AdminActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.de_ichiraku.MainActivity;
import com.example.de_ichiraku.R;

public class AdminCategory extends AppCompatActivity {

    private ImageView beverages,noodles;
    private Button LogoutBtn ,  CheckOrderBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_category);

        LogoutBtn = (Button) findViewById(R.id.admin_logout_btn);
        CheckOrderBtn = (Button) findViewById(R.id.check_order_btn);

        LogoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Intent intent = new Intent(AdminCategory.this, MainActivity.class);
              intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
              startActivity(intent);
              finish();
            }
        });

        CheckOrderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCategory.this, AdminNewOrderActivity.class);

                startActivity(intent);

            }
        });

        beverages = (ImageView) findViewById(R.id.beverages);
        noodles = (ImageView) findViewById(R.id.noodles);

        noodles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCategory.this, AdminAddProduct.class);
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