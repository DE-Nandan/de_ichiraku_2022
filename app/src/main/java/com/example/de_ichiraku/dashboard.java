package com.example.de_ichiraku;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.de_ichiraku.Model.Users;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

import io.paperdb.Paper;

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
//        String eml = "nandan@gmail.com";
//        HashMap <String,Object> userDataMap = new HashMap<>();
//        userDataMap.put("phone",phn);
//        userDataMap.put("email",eml);

        /*CREATING AN OBJECT TO BE STORED IN DATABASE*/
//      Users a = new Users("nandan","+918077746577");

        /*PASSING DATA USING MODEL CLASS TO FIREBASE*/
//        ref.child("Users").child(user.getUid()).setValue(a).addOnSuccessListener(new OnSuccessListener<Void>() {
//            @Override
//            public void onSuccess(Void unused) {
//                Toast.makeText(dashboard.this, "Added Successfully", Toast.LENGTH_SHORT).show();
//            }
//        });

        /* FOR RETRIEVING DATA*/
        //        ref.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                Users b = snapshot.child("Users").child(user.getUid()).getValue(Users.class);
//                if(b.getPassword() == "lolnation")
//                {
//                    Toast.makeText(dashboard.this,"correct",Toast.LENGTH_SHORT).show();
//                }
//            }

//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });

        Log.d("dfsf ",phn);
        btn = (Button)findViewById(R.id.Logout);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Paper.book().destroy();
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(dashboard.this, MainActivity.class);
                startActivity(intent);
                //finish();
            }
        });
    }
}