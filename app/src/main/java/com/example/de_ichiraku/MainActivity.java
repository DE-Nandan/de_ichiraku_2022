package com.example.de_ichiraku;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.de_ichiraku.Model.Users;
import com.example.de_ichiraku.Prevalent.Prevalent;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hbb20.CountryCodePicker;

import io.paperdb.Paper;

public class MainActivity extends AppCompatActivity {
    CountryCodePicker ccp;
    EditText t1;
    Button b1;
    FirebaseUser user;
    DatabaseReference ref;
    //Users a;
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
//        if(user == null)
//        {
//            Toast.makeText(this, "kl", Toast.LENGTH_SHORT).show();
//        }

        if(UserPhoneKey != "" && UserName != "")
        {
            if(!TextUtils.isEmpty(UserPhoneKey) && !TextUtils.isEmpty(UserName))
            {
                user = FirebaseAuth.getInstance().getCurrentUser();
//                if(user != null)
//                {
//                    Toast.makeText(this, user.getUid(), Toast.LENGTH_SHORT).show();
//                }
                ref = FirebaseDatabase.getInstance().getReference().child("Users").child(user.getUid());
                //Toast.makeText(this, user.getUid(), Toast.LENGTH_SHORT).show();
                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Users a= snapshot.getValue(Users.class);
                     //   Toast.makeText(MainActivity.this , a.getName(), Toast.LENGTH_SHORT).show();
                        Prevalent.currentOnlineUsers =a;
                      //  Toast.makeText(MainActivity.this , Prevalent.currentOnlineUsers.getPhone(), Toast.LENGTH_SHORT).show();

                        startActivity(new Intent(MainActivity.this, HomeActivity.class));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


              // Toast.makeText(this , Prevalent.currentOnlineUsers.getPhone(), Toast.LENGTH_SHORT).show();



            }
        }

    }
}