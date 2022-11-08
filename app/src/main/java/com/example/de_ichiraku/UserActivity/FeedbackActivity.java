package com.example.de_ichiraku.UserActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.de_ichiraku.Prevalent.Prevalent;
import com.example.de_ichiraku.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class FeedbackActivity extends AppCompatActivity {

    Button fbutton;
    String Rating;
    RatingBar rt;
    String prodID;
    int size;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);


        rt = (RatingBar) findViewById(R.id.ratingBar);
        //finding the specific RatingBar with its unique ID
        LayerDrawable stars=(LayerDrawable)rt.getProgressDrawable();

        Rating = String.valueOf(rt.getRating());
        Intent intent = getIntent();
        prodID  =intent.getStringExtra("prodID");
        //Use for changing the color of RatingBar
        stars.getDrawable(2).setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);

        DatabaseReference prodRef = FirebaseDatabase.getInstance().getReference().child("Products").child(prodID);
        DatabaseReference ratProdRef = prodRef.child("userR").child(Prevalent.currentOnlineUsers.getPhone());
        DatabaseReference userRef = prodRef.child("userR");


       HashMap <String,Object> rat = new HashMap<>();
       rat.put("rating","0");
       rat.put("state","no");

       ratProdRef.updateChildren(rat);

       userRef.addListenerForSingleValueEvent(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot snapshot) {
               size = (int) snapshot.getChildrenCount();
           }

           @Override
           public void onCancelled(@NonNull DatabaseError error) {

           }
       });










        fbutton = (Button) findViewById(R.id.fbutton);



        fbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ratProdRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                      // String ratingState = snapshot.child("state").getValue().toString();
                       // String userName = snapshot.child("name").getValue().toString();
                        //int usrR = (int) snapshot.child("rating").getValue();

                        double usrR = Double.valueOf(String.valueOf(snapshot.child("rating").getValue()));
                       // Toast.makeText(FeedbackActivity.this, usrR, Toast.LENGTH_SHORT).show();
                              prodRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                  @Override
                                  public void onDataChange(@NonNull DataSnapshot snapshot) {
                                      Double prevR = 0.0;
                                      if(snapshot.child("rating").exists())
                                      prevR = Double.valueOf(String.valueOf(snapshot.child("rating").getValue()));

                                      snapshot.getRef().child("rating").setValue((prevR+rt.getRating()-usrR)/size);
                                     // snapshot.getRef().child("state").setValue("yes");
                                  }

                                  @Override
                                  public void onCancelled(@NonNull DatabaseError error) {

                                  }
                              });
                             snapshot.getRef().child("rating").setValue(rt.getRating());


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });


    }
}