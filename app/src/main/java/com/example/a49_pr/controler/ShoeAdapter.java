package com.example.a49_pr.controler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a49_pr.R;
import com.example.a49_pr.model.Shoe;

import java.util.List;

public class ShoeAdapter extends RecyclerView.Adapter<ShoeAdapter.ShoeViewHolder> {
    private List<Shoe> shoes;

    public ShoeAdapter(List<Shoe> shoes) {
        this.shoes = shoes;
    }

    @NonNull
    @Override
    public ShoeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shoe_item, parent, false);
        return new ShoeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShoeViewHolder holder, int position) {
        Shoe shoe = shoes.get(position);
        holder.nameTextView.setText(shoe.name);
        holder.brandTextView.setText(shoe.brand);
        holder.sizeTextView.setText(String.valueOf(shoe.size));
        holder.priceTextView.setText(String.valueOf(shoe.price));
    }

    @Override
    public int getItemCount() {
        return shoes.size();
    }

    public Shoe getShoeAt(int position) {
        if (position >= 0 && position < shoes.size()) {
            return shoes.get(position);
        }
        return null;
    }

    static class ShoeViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView brandTextView;
        TextView sizeTextView;
        TextView priceTextView;

        public ShoeViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.shoe_name);
            brandTextView = itemView.findViewById(R.id.shoe_brand);
            sizeTextView = itemView.findViewById(R.id.shoe_size);
            priceTextView = itemView.findViewById(R.id.shoe_price);
        }
    }
}