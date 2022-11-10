package com.example.de_ichiraku;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class ManageOtp extends AppCompatActivity {

    EditText t2;
    Button b2;
    String phonenumber;
    String otpid;
    FirebaseAuth mAuth;
   private ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_otp);

        phonenumber = getIntent().getStringExtra("mobile").toString();
        t2 = (EditText)findViewById(R.id.t2);
        b2 = (Button)findViewById(R.id.b2);

        loadingBar = new ProgressDialog(this);
        loadingBar.setTitle("Verifying");
        loadingBar.setMessage("Checking credentials");
        loadingBar.setCanceledOnTouchOutside(false);
        mAuth = FirebaseAuth.getInstance();
        // If it is the same device where OTP is being sent then this function will run and proceed to next activity otherwise
        // it sets otpid var using the function given below
        initiateotp();

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(t2.getText().toString().isEmpty())
                    Toast.makeText(getApplicationContext(),"Blank Field",Toast.LENGTH_LONG).show();
                else if(t2.getText().toString().length() != 6)
                    Toast.makeText(getApplicationContext(),"Invalid",Toast.LENGTH_LONG).show();
                else
                {
                    //Creating PhoneAuth object which will be passes to checker function
                    PhoneAuthCredential credential = PhoneAuthProvider.getCredential(otpid,t2.getText().toString());
                    //Checker function takes the object
                    signInWithPhoneAuthCredential(credential);
                }
            }
        });

    }
    private void initiateotp()
    {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phonenumber,
                60,
                TimeUnit.SECONDS,
                this,
                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                        otpid = s;
                        //This function gets invoked when the OTP is sent is to another device i.e not the device in which the app is running

                    }

                    @Override
                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                       //We come here when we are ending OTP  on same device
                        signInWithPhoneAuthCredential(phoneAuthCredential);
                    }

                    @Override
                    public void onVerificationFailed(@NonNull FirebaseException e) {

                       // In case of failure this gets invoked
                        Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });
    }

    //Checker functions which initiates in both cases whether same device or different device
    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
       loadingBar.show();
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                          startActivity(new Intent(ManageOtp.this, AddUser.class));
                          finish();
                        } else {
                            Toast.makeText(getApplicationContext(),"Signin Error",Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }



}