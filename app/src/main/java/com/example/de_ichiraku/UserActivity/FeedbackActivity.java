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
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.de_ichiraku.Prevalent.Prevalent;
import com.example.de_ichiraku.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
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
    Double prevR;
    EditText fb_msg,fb_sub,fb_name;
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


        fb_name = (EditText)findViewById(R.id.fb_name);
        fb_sub = (EditText)findViewById(R.id.fb_subject);
        fb_msg = (EditText)findViewById(R.id.fb_msg);

        DatabaseReference prodRef = FirebaseDatabase.getInstance().getReference().child("Products").child(prodID);
        DatabaseReference ratProdRef = prodRef.child("userR").child(Prevalent.currentOnlineUsers.getPhone());
        DatabaseReference userRef = prodRef.child("userR");


        HashMap <String,Object> rat = new HashMap<>();
        rat.put("rating","0");
        rat.put("name"," ");
        rat.put("Subject"," ");
        rat.put("Feedback"," ");

        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())
                {
                    userRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            size = (int)snapshot.getChildrenCount();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }
                else
                {
                    ratProdRef.updateChildren(rat).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            userRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    size = (int)snapshot.getChildrenCount();
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




       // Toast.makeText(FeedbackActivity.this, size, Toast.LENGTH_SHORT).show();








        fbutton = (Button) findViewById(R.id.fbutton);



        fbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                ratProdRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        // String ratingState = snapshot.child("state").getValue().toString();
                        // String userName = snapshot.child("name").getValue().toString();
                        //int usrR = (int) snapshot.child("rating").getValue();

                        Double usrR = Double.valueOf(String.valueOf(snapshot.child("rating").getValue()));
                        // Toast.makeText(FeedbackActivity.this, usrR, Toast.LENGTH_SHORT).show();
                        prodRef.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {

                                    prevR = Double.valueOf(String.valueOf(snapshot.child("rating").getValue()));
                                    Double ans = (prevR+rt.getRating()-usrR)/size;
                                Toast.makeText(FeedbackActivity.this, prevR +" "+rt.getRating()+" "+usrR, Toast.LENGTH_SHORT).show();

                                snapshot.getRef().child("rating").setValue(String.valueOf(ans));
                                // snapshot.getRef().child("state").setValue("yes");
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                        snapshot.getRef().child("rating").setValue(String.valueOf(rt.getRating()));
                        snapshot.getRef().child("name").setValue(String.valueOf(fb_name.getText().toString()));
                        snapshot.getRef().child("Feedback").setValue(String.valueOf(fb_msg.getText().toString()));
                        snapshot.getRef().child("Subject").setValue(String.valueOf(fb_sub.getText().toString()));


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });


    }
}