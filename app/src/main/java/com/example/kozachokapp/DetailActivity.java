package com.example.kozachokapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kozachokapp.Adapter.DetailtAdapter;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {

    private TextView nameEvent;
    private RecyclerView recyclerView;
    private DetailtAdapter detailtAdapter;
    private ArrayList<Food> foods;
    private String nameFromMain;
    private int totalPriceCount;
    private TextView totalPrice;
    private ImageView imgView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ActionBar actionBar = getSupportActionBar();
        StringBuilder stringBuilder = new StringBuilder("");
        if (actionBar != null) {
            actionBar.hide();
        }
        totalPriceCount = 0;
        recyclerView = findViewById(R.id.rcSecond);
        nameEvent = findViewById(R.id.orderName);
        totalPrice = findViewById(R.id.totalPrice);
        detailtAdapter = new DetailtAdapter();
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("id")) {
            nameFromMain = intent.getStringExtra("id");
            foods = (ArrayList<Food>) getIntent().getSerializableExtra("list");
        } else {
            finish();
        }
        nameEvent.setText(nameFromMain);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(detailtAdapter);
        detailtAdapter.setFoods(foods);
        for(int i = 0; i < foods.size();i++){
            totalPriceCount += (foods.get(i).getPrice()*foods.get(i).getCount());
        }
        totalPrice.setText(Integer.toString(totalPriceCount));

        findViewById(R.id.imgShare).setOnClickListener(view -> {
            String textToList = "";
            char rep = '-';
//            Toast.makeText(this, ""+foods, Toast.LENGTH_SHORT).show();
            for (int i = 0; i < foods.size();i++){
               textToList = stringBuilder.append("Название: " + foods.get(i).getName() + " \nВес: " + foods.get(i).getWeight() + " г " + " \nЦена: " + foods.get(i).getPrice() + " грн " + " \nКоличество: "+ foods.get(i).getCount() + " шт.\n" +"\n").toString();
            }
            stringBuilder.insert(0,"Заказ: "+ nameFromMain.toString()+"\n\n");
            textToList = stringBuilder.append("Общая сумма заказа: "+totalPriceCount + " грн").toString();

            Intent intents = new Intent(Intent.ACTION_SEND);
            intents.setType("text/plain");
            intents.putExtra(Intent.EXTRA_TEXT, textToList);
            startActivity(Intent.createChooser(intents, "Поделиться"));
            stringBuilder.setLength(0);
        });


    }
}