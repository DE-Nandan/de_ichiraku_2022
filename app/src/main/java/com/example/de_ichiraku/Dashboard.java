package com.example.de_ichiraku;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import io.paperdb.Paper;

public class Dashboard extends AppCompatActivity {

    Button btn;
    Button btn2;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    String phn = user.getPhoneNumber().toString();
    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
    EditText edt;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);


        TextView textView2 = (TextView) findViewById(R.id.textView2);
        textView2.setText(phn);
//        String eml = "nandan@gmail.com";

/*CHECKING ADDITION OF DATA TO ALREADY LOGGED IN USER*/

//        HashMap <String,Object> userDataMap = new HashMap<>();





//        btn2 = (Button)findViewById(R.id.add);
//        btn2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                edt = (EditText)findViewById(R.id.editTextDash);
//                String st = edt.getText().toString();
//                userDataMap.put("newData",st);
//                ref.child("Users").child(user.getUid()).updateChildren(userDataMap).addOnSuccessListener(new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess(Void unused) {
//                        Toast.makeText(dashboard.this, "newAdded", Toast.LENGTH_SHORT).show();
//                    }
//                });
//            }
//        });

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
                Intent intent = new Intent(Dashboard.this, MainActivity.class);
                startActivity(intent);
                //finish();
            }
        });
    }
}