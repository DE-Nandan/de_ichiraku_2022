package com.example.de_ichiraku.UserActivity;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.de_ichiraku.Prevalent.Prevalent;
import com.example.de_ichiraku.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.ktx.Firebase;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;
import org.w3c.dom.Text;

public class PaymentActivity extends AppCompatActivity implements PaymentResultListener {

    private static final String TAG = PaymentActivity.class.getSimpleName();
    Button payBtn;
    TextView txtRes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        payBtn = (Button) findViewById(R.id.payBtn);
        txtRes = (TextView) findViewById(R.id.txtRes);

        payBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPayment();
            }
        });
    }

    public void startPayment() {

        Checkout checkout = new Checkout();
        checkout.setKeyID("rzp_test_ZWkn5g3yeWSvxB");
        /**
         * Instantiate Checkout
         */


        /**
         * Set your logo here
         */
        checkout.setImage(R.drawable.flag_andorra);

        /**
         * Reference to current activity
         */
        final Activity activity = this;

        /**
         * Pass your payment options to the Razorpay Checkout as a JSONObject
         */
        try {
            JSONObject options = new JSONObject();

            options.put("name", "Merchant Name");
            options.put("description", "Reference No. #123456");
            // options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.jpg");
            // options.put("order_id", "order_DBJOWzybf0sJbb");//from response of step 3.
            // options.put("theme.color", "#3399cc");
            options.put("currency", "INR");
            options.put("amount", "500");//pass amount in currency subunits

            JSONObject prefill = new JSONObject();
            prefill.put("prefill.email", " ");
            prefill.put("prefill.contact"," ");
            options.put("prefill",prefill);

//            JSONObject retryObj = new JSONObject();
//            retryObj.put("enabled", true);
//            retryObj.put("max_count", 4);
//            options.put("retry", retryObj);

            checkout.open(activity, options);

        } catch(Exception e) {
            Log.e(TAG, "Error in starting Razorpay Checkout", e);
        }
    }




    @Override
    public void onPaymentSuccess(String s) {
        cartClean();
        Toast.makeText(this, "Success "+s, Toast.LENGTH_SHORT).show();

    }

    private void cartClean() {
        FirebaseDatabase.getInstance().getReference().child("Cart List")
                .child("User View").child(Prevalent.currentOnlineUsers.getPhone()).removeValue()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(PaymentActivity.this, "Items removed successfully", Toast.LENGTH_SHORT).show();

//                                    Intent intent = new Intent(CompleteFinalOrderActivity.this,Explore.class);
//                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                                    startActivity(intent);
//                                    finish();
                    }
                });



        DatabaseReference orderRef;
        orderRef = FirebaseDatabase.getInstance().getReference().child("Orders").child(Prevalent.currentOnlineUsers.getPhone());
        orderRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())
                {
                    snapshot.getRef().child("state").setValue("shipped");

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        Intent intent = new Intent(PaymentActivity.this,HomeActivity.class);


    }

    @Override
    public void onPaymentError(int i, String s) {

        failOrder();


        Toast.makeText(this, "Error "+s, Toast.LENGTH_SHORT).show();
    }

    private void failOrder() {
        DatabaseReference orderRef;
        orderRef = FirebaseDatabase.getInstance().getReference().child("Orders").child(Prevalent.currentOnlineUsers.getPhone());
        orderRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())
                {
                   snapshot.getRef().child("state").setValue("failed");

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        Intent intent = new Intent(PaymentActivity.this,HomeActivity.class);

    }

}


