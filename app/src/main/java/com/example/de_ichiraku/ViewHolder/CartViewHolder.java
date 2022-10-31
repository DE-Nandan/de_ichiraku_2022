package com.example.de_ichiraku.ViewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.de_ichiraku.Interface.ItemClickListener;
import com.example.de_ichiraku.R;

public class CartViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView txtProductname, txtProductPrice , txtProductQunatity;
    private ItemClickListener itemClickListener;

    public CartViewHolder(@NonNull View itemView) {
        super(itemView);

        txtProductname = itemView.findViewById(R.id.cart_product_name);
        txtProductPrice = itemView.findViewById(R.id.cart_prodct_price);
        txtProductQunatity = itemView.findViewById(R.id.cart_product_quantity);


    }



    public void  setItemClickListener(ItemClickListener itemClickListener)
    {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v, getAdapterPosition(),false);

    }
}
