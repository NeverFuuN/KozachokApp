package com.example.kozachokapp.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kozachokapp.FoodCategory;
import com.example.kozachokapp.R;

import java.util.ArrayList;
import java.util.List;

public class FoodCatagotyAdapter extends RecyclerView.Adapter<FoodCatagotyAdapter.FoodCategoryViewHolder> {
        private List<FoodCategory> foodCategories;
        private onItemsClickListener onItemsClickListener;

        public void setOnItemsClickListener(onItemsClickListener onItemsClickListener) {
            this.onItemsClickListener = onItemsClickListener;
            notifyDataSetChanged();
        }

        public void setFoodCategories(List<FoodCategory> foodCategories) {
            this.foodCategories = foodCategories;
            notifyDataSetChanged();
        }

        @NonNull
        @Override
        public FoodCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_horizontal, parent, false);
            return new FoodCategoryViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull FoodCategoryViewHolder holder, int position) {
            FoodCategory foodCategory = foodCategories.get(position);
            holder.nameCategory.setText(foodCategory.getNameCat());
        }

        @Override
        public int getItemCount() {
            if (foodCategories == null) {
                return 0;
            }
            return foodCategories.size();
        }

        public interface onItemsClickListener {
            void onitemClick(int position);
        }

        class FoodCategoryViewHolder extends RecyclerView.ViewHolder {
            TextView nameCategory;

            public FoodCategoryViewHolder(@NonNull View itemView) {
                super(itemView);
                nameCategory = itemView.findViewById(R.id.nameofCat);
                itemView.setOnClickListener(view -> {
                    onItemsClickListener.onitemClick(getAdapterPosition());

                });
            }
        }
    }
