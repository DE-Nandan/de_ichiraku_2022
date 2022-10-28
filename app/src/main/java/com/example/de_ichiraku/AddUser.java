package com.example.de_ichiraku;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.de_ichiraku.Model.Users;
import com.example.de_ichiraku.Prevalent.Prevalent;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import io.paperdb.Paper;

public class AddUser extends AppCompatActivity {

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
    String phn = user.getPhoneNumber().toString();
    EditText t3;
    Button b3;
    private CheckBox chkBoxRemMe;
    TextView isAdmin;
    TextView isUser;
    String parentDbName = "Users";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);



        chkBoxRemMe = (CheckBox)findViewById(R.id.checkBox);
        b3 = (Button) findViewById(R.id.b3);
        t3 = (EditText) findViewById(R.id.editTextnm);
        isAdmin= (TextView)findViewById(R.id.isAdmin);
        isUser= (TextView)findViewById(R.id.isUser);
        Paper.init(this);

        Log.d("namecheck ",t3.getText().toString());
//        Users a = new Users(nm, "87878787878");
//        System.out.println(nm);

       b3.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String nm = t3.getText().toString();
               Users a = new Users(nm, phn);

               if(chkBoxRemMe.isChecked())
               {
                   Paper.book().write(Prevalent.UserPhoneKey,phn);
                   Paper.book().write(Prevalent.UserName,nm);
               }

               ref.child(parentDbName).child(user.getUid()).setValue(a).addOnSuccessListener(new OnSuccessListener<Void>() {
                   @Override
                   public void onSuccess(Void unused) {
                       Toast.makeText(AddUser.this, "Added Successfully", Toast.LENGTH_SHORT).show();
                   }
               });

               Intent intent;
               if(parentDbName.equals("Users")) {
                   intent = new Intent(AddUser.this, Dashboard.class);
               }
               else{
                   intent = new Intent(AddUser.this, AdminCategory.class);
               }

               startActivity(intent);


           }
       });

       isUser.setVisibility(View.INVISIBLE);

       isAdmin.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               b3.setText("Login Admin");
               isAdmin.setVisibility(View.INVISIBLE);
               isUser.setVisibility(View.VISIBLE);
               chkBoxRemMe.setVisibility(View.INVISIBLE);
               parentDbName = "Admins";
           }
       });

       isUser.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               b3.setText("Login");
               isAdmin.setVisibility(View.VISIBLE);
               isUser.setVisibility(View.INVISIBLE);
               chkBoxRemMe.setVisibility(View.VISIBLE);
               parentDbName = "Users";
           }
       });


    }
}