package com.example.de_ichiraku;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.de_ichiraku.Model.Users;
import com.example.de_ichiraku.Prevalent.Prevalent;
import com.example.de_ichiraku.UserActivity.HomeActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hbb20.CountryCodePicker;

import io.paperdb.Paper;

public class MainActivity extends AppCompatActivity  {
    CountryCodePicker ccp;
    EditText t1;
    Button b1;
    FirebaseUser user;
    DatabaseReference ref;
    private ProgressDialog loadingBar;

    //Users a;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        t1 = (EditText) findViewById(R.id.t1);
        ccp = (CountryCodePicker) findViewById(R.id.ccp);
        ccp.registerCarrierNumberEditText(t1);
        b1 = (Button) findViewById(R.id.b1);
        loadingBar = new ProgressDialog(this);
        loadingBar.setTitle("Welcome!");
        loadingBar.setMessage("Loading...");
        loadingBar.setCanceledOnTouchOutside(false);

        /*
        * Basically Paper is used to check data int the memory of android
        * if there is some memory then we use that for different functionalities
        * here we are using it for "Remember me function for Login"
        * */
        Paper.init(this);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ManageOtp.class);
                //taking number from Main Activity Class to Manage Activity class as it would be required for further Authentication process
                intent.putExtra("mobile",ccp.getFullNumberWithPlus().replace(" ",""));
                startActivity(intent);
            }
        });








        String UserPhoneKey = Paper.book().read(Prevalent.UserPhoneKey);
        String UserName = Paper.book().read(Prevalent.UserName);


        if(UserPhoneKey != "" && UserName != "")
        {
            // checking if android memory contains some data and if it is there directing the user to Home Page
            if(!TextUtils.isEmpty(UserPhoneKey) && !TextUtils.isEmpty(UserName))
            {
                loadingBar.show();
                user = FirebaseAuth.getInstance().getCurrentUser();
//
                ref = FirebaseDatabase.getInstance().getReference().child("Users").child(user.getUid());
                //Toast.makeText(this, user.getUid(), Toast.LENGTH_SHORT).show();
                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        // Firstly storing data of snapshot in User class and then assigning it to an object of User
                        Users a= snapshot.getValue(Users.class);
                        // Setting up Prevalent user data as the data fetched from firebase database
                        Prevalent.currentOnlineUsers =a;


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