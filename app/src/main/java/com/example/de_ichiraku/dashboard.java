package com.example.de_ichiraku;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class dashboard extends AppCompatActivity {

    Button btn;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    String phn = user.getPhoneNumber().toString();
    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);


        TextView textView2 = (TextView) findViewById(R.id.textView2);
        textView2.setText(phn);
        String eml = "nandan@gmail.com";
        HashMap <String,Object> userDataMap = new HashMap<>();
        userDataMap.put("phone",phn);
        userDataMap.put("email",eml);
        ref.child("Users").child(user.getUid()).setValue(userDataMap);

        Log.d("dfsf ",phn);
        btn = (Button)findViewById(R.id.Logout);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                finish();
            }
        });
    }
}