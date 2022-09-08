package com.example.kozachokapp.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kozachokapp.Food;
import com.example.kozachokapp.R;

import java.util.ArrayList;

public class DetailtAdapter extends RecyclerView.Adapter<DetailtAdapter.DetailtAdapterViewHolder> {

    public ArrayList<Food> foods;

    public void setFoods(ArrayList<Food> foods) {
        this.foods = foods;
    }

    @NonNull
    @Override
    public DetailtAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_details, parent, false);
        return new DetailtAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailtAdapterViewHolder holder, int position) {
        Food food = foods.get(position);
        holder.foodName.setText(food.getName());
        holder.foodWeight.setText(Integer.toString(food.getWeight()));
        holder.foodPrice.setText(Double.toString(food.getPrice()));
        holder.foodCount.setText(Integer.toString(food.getCount()));

    }

    @Override
    public int getItemCount() {
        if (foods == null) {
            return 0;
        }
        return foods.size();
    }

    class DetailtAdapterViewHolder extends RecyclerView.ViewHolder {
        TextView foodName;
        TextView foodWeight;
        TextView foodPrice;
        TextView foodCount;

        public DetailtAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            foodName = itemView.findViewById(R.id.foodName);
            foodWeight = itemView.findViewById(R.id.foodWeight);
            foodPrice = itemView.findViewById(R.id.foodPrice);
            foodCount = itemView.findViewById(R.id.foodCount);
        }
    }
}
