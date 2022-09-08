package com.example.kozachokapp.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kozachokapp.Order;
import com.example.kozachokapp.R;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainOrderViewHolder> {

    public ArrayList<Order> orderLists;
    public onOrderClickListener onOrderClickListener;

    public void setOnOrderClickListener(onOrderClickListener onOrderClickListener) {
        this.onOrderClickListener = onOrderClickListener;
        notifyDataSetChanged();
    }

    public void setOrderLists(ArrayList<Order> orderLists) {
        this.orderLists = orderLists;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MainOrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_main_menu, parent, false);
        return new MainOrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainOrderViewHolder holder, int position) {
//        OrderList orderList = orderLists.get(position);
        Order order = orderLists.get(position);
        holder.nameOrder.setText(order.getOrderName());

    }

    @Override
    public int getItemCount() {
        if (orderLists == null) {
            return 0;
        }
        return orderLists.size();
    }

    public interface onOrderClickListener {
        void onOrderClick(int position);

    }

    class MainOrderViewHolder extends RecyclerView.ViewHolder {

        TextView nameOrder;

        public MainOrderViewHolder(@NonNull View itemView) {
            super(itemView);
            nameOrder = itemView.findViewById(R.id.nameOrder);
            itemView.setOnClickListener(view -> {
                onOrderClickListener.onOrderClick(getAdapterPosition());
//                Toast.makeText(itemView.getContext(), ""+getAdapterPosition(), Toast.LENGTH_SHORT).show();
            });
        }
    }
}
