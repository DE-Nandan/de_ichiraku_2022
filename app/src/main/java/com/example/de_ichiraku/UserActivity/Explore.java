package com.example.de_ichiraku.UserActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.de_ichiraku.Model.Products;
import com.example.de_ichiraku.R;
import com.example.de_ichiraku.ViewHolder.ProductViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import io.paperdb.Paper;

public class Explore extends AppCompatActivity {

    private DatabaseReference ProductRef;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore);

        ProductRef = FirebaseDatabase.getInstance().getReference().child("Products");
        Paper.init(this);

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        toolbar.setTitle("Home");
//        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab2);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Explore.this, CartActivity.class);
               startActivity(intent);
            }
        });

        recyclerView = findViewById(R.id.recycler_menu2);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);



    }

    @Override
    protected void onStart() {
        super.onStart();

                FirebaseRecyclerOptions<Products> options =
                new FirebaseRecyclerOptions.Builder<Products>()
                .setQuery(ProductRef,Products.class)
                .build();

        FirebaseRecyclerAdapter<Products, ProductViewHolder> adapter =
                new FirebaseRecyclerAdapter<Products, ProductViewHolder> (options) {
                    @Override
                    protected void onBindViewHolder(@NonNull ProductViewHolder holder, int position, @NonNull Products model) {
                       holder.txtProductName.setText(model.getPname());
                        holder.txtProductDescription.setText(model.getDescription());
                        holder.txtProductPrice.setText( model.getPrice());
                        holder.txtRating.setText("Rating: "+model.getRating()+"/5");

                       // Toast.makeText(Explore.this, model.getImage().toString(), Toast.LENGTH_LONG).show();
                     //   Log.d("imer",model.getImage());
                       Picasso.get().load(model.getImg()).into(holder.imageView);

                       holder.itemView.setOnClickListener(new View.OnClickListener() {
                           @Override
                           public void onClick(View v) {

                               Intent intent = new Intent(Explore.this, ProductDetailsActivity.class);
                               intent.putExtra("pid",model.getPid());
                               startActivity(intent);
                           }
                       });
                    }

                    @NonNull
                    @Override
                    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_items_layout, parent,false);
                        ProductViewHolder holder = new ProductViewHolder(view);
                        return holder;
                    }
                };

        recyclerView.setAdapter(adapter);
        adapter.startListening();


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(Explore.this, HomeActivity.class);
        startActivity(intent);
    }
}

