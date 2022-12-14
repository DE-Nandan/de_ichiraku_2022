package com.example.de_ichiraku.ViewHolder;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.de_ichiraku.Interface.ItemClickListener;
import com.example.de_ichiraku.R;

public class ProductViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder implements View.OnClickListener
{
    public TextView txtProductName, txtProductDescription, txtProductPrice,txtRating;
    public ImageView imageView;
    public ItemClickListener listener;



    public ProductViewHolder(View itemView)
    {
        super(itemView);


        imageView = (ImageView)  itemView.findViewById(R.id.product_image3);
        txtProductName = (TextView) itemView.findViewById(R.id.product_name);
        txtProductDescription = (TextView) itemView.findViewById(R.id.product_description);
        txtProductPrice = (TextView) itemView.findViewById(R.id.product_price);
        txtRating = (TextView) itemView.findViewById(R.id.pro_rat);


    }

    public  void setItemClickListener(ItemClickListener listener)
    {
        this.listener = listener;
    }


    @Override
    public void onClick(View v) {
     listener.onClick(v, getAdapterPosition(),false);
    }
}
