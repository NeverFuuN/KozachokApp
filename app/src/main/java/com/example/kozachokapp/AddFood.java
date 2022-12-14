package com.example.kozachokapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kozachokapp.Adapter.FoodCatagotyAdapter;
import com.example.kozachokapp.Adapter.FoodMenuAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class AddFood extends AppCompatActivity {

    private static final String KEY = "MENUS";
    private RecyclerView rcHorizontal;
    private RecyclerView rcMenu;
    private ArrayList<Food> salats;
    private ArrayList<Food> salats2;
    private ArrayList<Food> salats3;
    private ArrayList<Food> salats4;
    private ArrayList<Food> itemsInCart;
    private ArrayList<FoodCategory> foodCategory;
    private FoodMenuAdapter adapterMenu;
    private FoodCatagotyAdapter adapterCat;
    private FirebaseFirestore db;
    private DatabaseReference myRef;
    private FirebaseDatabase database;
    private AddFoodViewModel foodViewModel;
    private Button btnAdd;
    private  int countPoss=0;
    private double pricePoss=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);
//        foodCategory = new ArrayList<>();
//        salats = new ArrayList<>();
//        salats2 = new ArrayList<>();
//        salats3 = new ArrayList<>();
//        salats4 = new ArrayList<>();
//        setMenu();
        btnAdd = findViewById(R.id.btnCrateOrder);
        db = FirebaseFirestore.getInstance();
        database = FirebaseDatabase.getInstance();

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        foodViewModel = new ViewModelProvider(this).get(AddFoodViewModel.class);
        foodViewModel.getFoods().observe(this, new Observer<List<Food>>() {
            @Override
            public void onChanged(List<Food> foods) {
                adapterMenu.setFoodList(foods);
            }
        });
        foodViewModel.getFoodCategory().observe(this, new Observer<List<FoodCategory>>() {
            @Override
            public void onChanged(List<FoodCategory> foodCategories) {
                adapterCat.setFoodCategories(foodCategories);

                adapterCat.setOnItemsClickListener(position -> {
                    adapterMenu.setFoodList(foodCategories.get(position).getList());
                });
            }
        });

        rcMenu = findViewById(R.id.rcItemList);
        rcMenu.setLayoutManager(new GridLayoutManager(this, getColumnCount()));
        adapterMenu = new FoodMenuAdapter();
//        adapterMenu.setFoodList(salats);
        rcMenu.setAdapter(adapterMenu);

        rcHorizontal = findViewById(R.id.rcHorizontalType);
        rcHorizontal.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        adapterCat = new FoodCatagotyAdapter();
//        adapterCat.setFoodCategories(foodCategory);
        rcHorizontal.setAdapter(adapterCat);

        addOrder();

//        adapterCat.setOnItemsClickListener(position -> {
//
//            foodViewModel.getFoodCategory().observe(this, new Observer<List<FoodCategory>>() {
//                @Override
//                public void onChanged(List<FoodCategory> foodCategories) {
//                    adapterCat.setFoodCategories(foodCategories);
//                    adapterMenu.setFoodList(foodCategories.get(position).getList());
//                }
//            });
//        });
        adapterMenu.setClickListener(new FoodMenuAdapter.MenuListClickListener() {
            @Override
            public void onAddToCartClick(Food food) {
                if (itemsInCart == null) {
                    itemsInCart = new ArrayList<>();
                }
                itemsInCart.add(food);
                Log.i("TAG", "??????????   " + food + "   " + itemsInCart);
            }

            @Override
            public void onUpdateCartClick(Food food) {
                if (itemsInCart.contains(food)) {

                    int index = itemsInCart.indexOf(food);
                    itemsInCart.remove(index);
                    itemsInCart.add(index, food);

                }
            }

            @Override
            public void onRemoveToCartClick(Food food) {
                if (itemsInCart.contains(food)) {
                    itemsInCart.remove(food);
//                    Toast.makeText(AddFood.this, ""+food, Toast.LENGTH_SHORT).show();
                    Log.i("TAG", "??????????   " + food + "   " + itemsInCart);
                }

            }
        });

    }


    private int getColumnCount(){
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width =(int) (displayMetrics.widthPixels/ displayMetrics.density);
        return width/185 > 2? width/185 : 2;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        outState.putSerializable(KEY, itemsInCart);
        super.onSaveInstanceState(outState);
    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        itemsInCart = (ArrayList<Food>) savedInstanceState.getSerializable(KEY);

    }

//    private void setMenu() {
//
//        foodCategory.add(new FoodCategory("????????????????", salats2));
//        foodCategory.add(new FoodCategory("????????????", salats));
//        foodCategory.add(new FoodCategory("???????????????? ??????????????", salats3));
//        foodCategory.add(new FoodCategory("?????????????? ??????????????", salats4));
//        foodCategory.add(new FoodCategory("????????", salats));
//        foodCategory.add(new FoodCategory("????????", salats));
//        foodCategory.add(new FoodCategory("????????????", salats));
//        foodCategory.add(new FoodCategory("??????????????", salats));
//        foodCategory.add(new FoodCategory("??????????????", salats));
//
//
//        salats.add(new Food("??????????", 250, 12, 0));
//        salats.add(new Food("????????????????????????????1", 210, 1265, 0));
//        salats.add(new Food("??????", 220, 125, 0));
//        salats.add(new Food("??????", 230, 125, 0));
//        salats.add(new Food("??????", 2400, 1201, 0));
//        salats.add(new Food("??????", 250, 15, 0));
//        salats.add(new Food("??????", 260, 185, 0));
//        salats.add(new Food("??????", 270, 125, 0));
//        salats.add(new Food("??????", 280, 125, 0));
//        salats.add(new Food("??????", 290, 125, 0));
//        salats.add(new Food("??????0", 310, 125, 0));
//        salats.add(new Food("??????1", 320, 125, 0));
//        salats.add(new Food("??????2", 330, 125, 0));
//        salats.add(new Food("??????3", 340, 125, 0));
//        salats.add(new Food("??????4", 2330, 125, 0));
//        salats.add(new Food("??????5", 4350, 125, 0));
//        salats.add(new Food("??????6", 2150, 125, 0));
//
//        salats2.add(new Food("??????????", 250, 12, 0));
//        salats2.add(new Food("????????????????????????????1", 210, 1265, 0));
//        salats2.add(new Food("????2", 220, 125, 0));
//        salats2.add(new Food("????3", 230, 125, 0));
//        salats2.add(new Food("????4", 2400, 1201, 0));
//        salats2.add(new Food("????5", 250, 15, 0));
//        salats2.add(new Food("????6", 260, 185, 0));
//        salats2.add(new Food("????7", 270, 125, 0));
//        salats2.add(new Food("????8", 280, 125, 0));
//        salats2.add(new Food("????9", 290, 125, 0));
//        salats2.add(new Food("????10", 310, 125, 0));
//        salats2.add(new Food("????11", 320, 125, 0));
//        salats2.add(new Food("????12", 330, 125, 0));
//        salats2.add(new Food("????13", 340, 125, 0));
//        salats2.add(new Food("????14", 2330, 125, 0));
//        salats2.add(new Food("????15", 4350, 125, 0));
//        salats2.add(new Food("????16", 2150, 125, 0));
//
//        salats3.add(new Food("????????", 250, 12, 0));
//        salats3.add(new Food("??????????????????????????1", 210, 1265, 0));
//        salats3.add(new Food("??2", 220, 125, 0));
//        salats3.add(new Food("??3", 23, 125, 0));
//        salats3.add(new Food("??4", 240, 1201, 0));
//        salats3.add(new Food("??5", 25, 15, 0));
//        salats3.add(new Food("??6", 26, 185, 0));
//        salats3.add(new Food("??7", 27, 125, 0));
//        salats3.add(new Food("??8", 28, 125, 0));
//        salats3.add(new Food("??9", 29, 125, 0));
//        salats3.add(new Food("??10", 30, 125, 0));
//        salats3.add(new Food("??11", 30, 125, 0));
//        salats3.add(new Food("??12", 30, 125, 0));
//        salats3.add(new Food("??13", 30, 125, 0));
//        salats3.add(new Food("??14", 230, 125, 0));
//        salats3.add(new Food("??15", 450, 125, 0));
//        salats3.add(new Food("??16", 250, 125, 0));
//
//    }

    void addOrder() {

        findViewById(R.id.btnCrateOrder).setOnClickListener(view -> {
            countPoss=0;
            pricePoss = 0;
            if (itemsInCart != null) {
                for(int i=0; i<itemsInCart.size(); i++){
                    countPoss += itemsInCart.get(i).getCount();
                    pricePoss += (itemsInCart.get(i).getCount() * itemsInCart.get(i).getPrice());
                }
            }

//            db.collection("events")
//                    .add(listFood)
//                    .addOnSuccessListener(documentReference -> {
//                        Toast.makeText(AddFood.this, "?????????? ????????????????", Toast.LENGTH_SHORT).show();
//                    })
//                    .addOnFailureListener(e -> {
//                        Toast.makeText(AddFood.this, "???????????? " + e.getMessage(), Toast.LENGTH_SHORT).show();
//                    });
            //?????????????????????? ????????
            if(itemsInCart != null){
                AlertDialog.Builder myDialog = new AlertDialog.Builder(this);
                myDialog.setTitle("?????????????????????? ????????????");
                myDialog.setMessage("???????????????????? ??????????????: "+ countPoss + " ????."+"\n?????????? ?????????? ????????????: "+ pricePoss+" ??????."+"\n\n ?????????????? ???????????????? ????????????:");
                EditText etName = new EditText(this);
                myDialog.setView(etName);
                myDialog.setPositiveButton("??????????????????", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String nameOrder = etName.getText().toString();
//                    Toast.makeText(AddFood.this, "" + nameOrder, Toast.LENGTH_SHORT).show();
                        myRef = database.getReference("orders");
                        if(!nameOrder.isEmpty()){
                            myRef.child(nameOrder).setValue((new Order(nameOrder, itemsInCart)));
                            Intent intent = new Intent(AddFood.this, MainActivity.class);
                            startActivity(intent);
                        }else {
                            Toast.makeText(AddFood.this, "?????????????? ?????? ????????????", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                myDialog.setNegativeButton("????????????????", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                myDialog.show();
            }else {
                Toast.makeText(this, "???????????????? ??????????????", Toast.LENGTH_SHORT).show();
            }

        });
    }
}
