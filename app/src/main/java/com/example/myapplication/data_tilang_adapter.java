package com.example.myapplication;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class data_tilang_adapter extends RecyclerView.Adapter<data_tilang_adapter.ProductViewHolder>{
    private Context mCtx;
    private List<data_tilang> productList;

    public data_tilang_adapter(Context mCtx, List<data_tilang> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("LAYOUT", LayoutInflater.from(mCtx).inflate(R.layout.history_list, parent, false).toString());
        return new ProductViewHolder(
                LayoutInflater.from(mCtx).inflate(R.layout.history_list, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        data_tilang product = productList.get(position);
        holder.nomorsurat.setText(product.getNomorsurat());
        holder.nomorktp.setText(product.getNomorktp());
        Log.d("ORANG", product.getNomorktp());
    }
    @Override
    public int getItemCount() {
        return productList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView nomorsurat, nomorktp;

        public ProductViewHolder(View itemView) {
            super(itemView);
            nomorsurat = itemView.findViewById(R.id.NomorSurat);
            nomorktp = itemView.findViewById(R.id.NomorKTP);
        }
    }
}
