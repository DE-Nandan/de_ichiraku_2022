package com.example.de_ichiraku.ViewHolder;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.de_ichiraku.Interface.ItemClickListener;
import com.example.de_ichiraku.R;

public class AdminOrdersViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder implements View.OnClickListener
{
    public TextView userName , userPhone , userTotalPrice , userDateTime , userShippingAddress;
    public Button ShowOrderBtn;
    public ItemClickListener listener;


    public AdminOrdersViewHolder(View itemView)
    {
        super(itemView);


       userDateTime = itemView.findViewById(R.id.order_date_time);
       userPhone = itemView.findViewById(R.id.order_phone_number);
       userTotalPrice = itemView.findViewById(R.id.order_total_price);
       userShippingAddress = itemView.findViewById(R.id.order_address_city);
       ShowOrderBtn= itemView.findViewById(R.id.show_products_btn);
       userName = itemView.findViewById(R.id.order_user_name);

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
