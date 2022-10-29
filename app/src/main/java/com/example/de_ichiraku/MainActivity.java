package com.example.de_ichiraku;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.de_ichiraku.Prevalent.Prevalent;
import com.hbb20.CountryCodePicker;

import io.paperdb.Paper;

public class MainActivity extends AppCompatActivity {
    CountryCodePicker ccp;
    EditText t1;
    Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        t1 = (EditText) findViewById(R.id.t1);
        ccp = (CountryCodePicker) findViewById(R.id.ccp);
        ccp.registerCarrierNumberEditText(t1);
        b1 = (Button) findViewById(R.id.b1);

        Paper.init(this);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ManageOtp.class);
                intent.putExtra("mobile",ccp.getFullNumberWithPlus().replace(" ",""));
                startActivity(intent);
            }
        });

        String UserPhoneKey = Paper.book().read(Prevalent.UserPhoneKey);
        String UserName = Paper.book().read(Prevalent.UserName);

        if(UserPhoneKey != "" && UserName != "")
        {
            if(!TextUtils.isEmpty(UserPhoneKey) && !TextUtils.isEmpty(UserName))
            {

                startActivity(new Intent(MainActivity.this, HomeActivity.class));
            }
        }

    }
}