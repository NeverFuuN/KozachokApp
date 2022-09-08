package com.example.kozachokapp.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kozachokapp.Food;
import com.example.kozachokapp.R;

import java.util.ArrayList;
import java.util.List;


public class FoodMenuAdapter extends RecyclerView.Adapter<FoodMenuAdapter.FoodMenyViewHolder> {

    public List<Food> foodList;
    public MenuListClickListener clickListener;


    public void setFoodList(List<Food> foodList) {
        this.foodList = foodList;
        notifyDataSetChanged();
    }

    public FoodMenuAdapter() {
    }

    public void setClickListener(MenuListClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public FoodMenyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_position, parent, false);
        return new FoodMenyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodMenyViewHolder holder, int position) {
        Food food = foodList.get(position);
        holder.namePositon.setText(food.getName());
        holder.weightPositon.setText(Integer.toString(food.getWeight()));
        holder.pricePositon.setText(Double.toString(food.getPrice()));

        if (food.getCount() == 0) {
            holder.btnAddPos.setOnClickListener(view -> {
                food.setCount(1);
                clickListener.onAddToCartClick(food);
                holder.btnAddPos.setVisibility(View.GONE);
                holder.plus.setVisibility(View.VISIBLE);
                holder.minus.setVisibility(View.VISIBLE);
                holder.countOfPos.setVisibility(View.VISIBLE);
                holder.countOfPos.setText(Integer.toString(food.getCount()));

            });
        }else {
            holder.countOfPos.setText(Integer.toString(food.getCount()));
            holder.btnAddPos.setVisibility(View.GONE);
            holder.plus.setVisibility(View.VISIBLE);
            holder.minus.setVisibility(View.VISIBLE);
            holder.countOfPos.setVisibility(View.VISIBLE);
        }
        holder.plus.setOnClickListener(view -> {
            int total = food.getCount();
            total++;
            food.setCount(total);
            holder.countOfPos.setText(Integer.toString(total));
            clickListener.onUpdateCartClick(food);
        });
        holder.minus.setOnClickListener(view -> {
            int total = food.getCount();
            total--;
            food.setCount(total);
            clickListener.onUpdateCartClick(food);
            if (total < 1) {
                holder.btnAddPos.setVisibility(View.VISIBLE);
                holder.plus.setVisibility(View.GONE);
                holder.minus.setVisibility(View.GONE);
                holder.countOfPos.setVisibility(View.GONE);
                clickListener.onRemoveToCartClick(food);
            }
            holder.countOfPos.setText((Integer.toString(total)));
        });

    }

    @Override
    public int getItemCount() {
        if (foodList == null) {
            return 0;
        }
        return foodList.size();
    }

    class FoodMenyViewHolder extends RecyclerView.ViewHolder {

        private TextView namePositon;
        private TextView weightPositon;
        private TextView pricePositon;
        private TextView countOfPos;
        private ImageButton minus, plus;
        private Button btnAddPos;


        public FoodMenyViewHolder(@NonNull View itemView) {
            super(itemView);
            namePositon = itemView.findViewById(R.id.nameOfItemsMenu);
            weightPositon = itemView.findViewById(R.id.txtVes);
            pricePositon = itemView.findViewById(R.id.txtPrice);
            countOfPos = itemView.findViewById(R.id.itemCounter);
            minus = itemView.findViewById(R.id.btnRemoveCount);
            plus = itemView.findViewById(R.id.btnAddCount);
            btnAddPos = itemView.findViewById(R.id.btnAddPosition);

        }
    }

    public interface MenuListClickListener {
        public void onAddToCartClick(Food food);

        public void onUpdateCartClick(Food food);

        public void onRemoveToCartClick(Food food);
    }
}
