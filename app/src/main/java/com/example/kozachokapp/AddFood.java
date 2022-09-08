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
                Log.i("TAG", "ОРДЕР   " + food + "   " + itemsInCart);
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
                    Log.i("TAG", "ОРДЕР   " + food + "   " + itemsInCart);
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
//        foodCategory.add(new FoodCategory("Пательни", salats2));
//        foodCategory.add(new FoodCategory("Салаты", salats));
//        foodCategory.add(new FoodCategory("Холодные закуски", salats3));
//        foodCategory.add(new FoodCategory("Горячие закуски", salats4));
//        foodCategory.add(new FoodCategory("Рыба", salats));
//        foodCategory.add(new FoodCategory("Мясо", salats));
//        foodCategory.add(new FoodCategory("Мангал", salats));
//        foodCategory.add(new FoodCategory("Гарниры", salats));
//        foodCategory.add(new FoodCategory("Десерты", salats));
//
//
//        salats.add(new Food("Цезрь", 250, 12, 0));
//        salats.add(new Food("тесаыфафыфыафа1", 210, 1265, 0));
//        salats.add(new Food("тес", 220, 125, 0));
//        salats.add(new Food("тес", 230, 125, 0));
//        salats.add(new Food("тес", 2400, 1201, 0));
//        salats.add(new Food("тес", 250, 15, 0));
//        salats.add(new Food("тес", 260, 185, 0));
//        salats.add(new Food("тес", 270, 125, 0));
//        salats.add(new Food("тес", 280, 125, 0));
//        salats.add(new Food("тес", 290, 125, 0));
//        salats.add(new Food("тес0", 310, 125, 0));
//        salats.add(new Food("тес1", 320, 125, 0));
//        salats.add(new Food("тес2", 330, 125, 0));
//        salats.add(new Food("тес3", 340, 125, 0));
//        salats.add(new Food("тес4", 2330, 125, 0));
//        salats.add(new Food("тес5", 4350, 125, 0));
//        salats.add(new Food("тес6", 2150, 125, 0));
//
//        salats2.add(new Food("езарь", 250, 12, 0));
//        salats2.add(new Food("есыаыфафыфыафа1", 210, 1265, 0));
//        salats2.add(new Food("ес2", 220, 125, 0));
//        salats2.add(new Food("ес3", 230, 125, 0));
//        salats2.add(new Food("ес4", 2400, 1201, 0));
//        salats2.add(new Food("ес5", 250, 15, 0));
//        salats2.add(new Food("ес6", 260, 185, 0));
//        salats2.add(new Food("ес7", 270, 125, 0));
//        salats2.add(new Food("ес8", 280, 125, 0));
//        salats2.add(new Food("ес9", 290, 125, 0));
//        salats2.add(new Food("ес10", 310, 125, 0));
//        salats2.add(new Food("ес11", 320, 125, 0));
//        salats2.add(new Food("ес12", 330, 125, 0));
//        salats2.add(new Food("ес13", 340, 125, 0));
//        salats2.add(new Food("ес14", 2330, 125, 0));
//        salats2.add(new Food("ес15", 4350, 125, 0));
//        salats2.add(new Food("ес16", 2150, 125, 0));
//
//        salats3.add(new Food("Царь", 250, 12, 0));
//        salats3.add(new Food("тыаыфафыфыафа1", 210, 1265, 0));
//        salats3.add(new Food("т2", 220, 125, 0));
//        salats3.add(new Food("т3", 23, 125, 0));
//        salats3.add(new Food("т4", 240, 1201, 0));
//        salats3.add(new Food("т5", 25, 15, 0));
//        salats3.add(new Food("т6", 26, 185, 0));
//        salats3.add(new Food("т7", 27, 125, 0));
//        salats3.add(new Food("т8", 28, 125, 0));
//        salats3.add(new Food("т9", 29, 125, 0));
//        salats3.add(new Food("т10", 30, 125, 0));
//        salats3.add(new Food("т11", 30, 125, 0));
//        salats3.add(new Food("т12", 30, 125, 0));
//        salats3.add(new Food("т13", 30, 125, 0));
//        salats3.add(new Food("т14", 230, 125, 0));
//        salats3.add(new Food("т15", 450, 125, 0));
//        salats3.add(new Food("с16", 250, 125, 0));
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
//                        Toast.makeText(AddFood.this, "Заказ добавлен", Toast.LENGTH_SHORT).show();
//                    })
//                    .addOnFailureListener(e -> {
//                        Toast.makeText(AddFood.this, "Ошибка " + e.getMessage(), Toast.LENGTH_SHORT).show();
//                    });
            //Всплывающее окно
            if(itemsInCart != null){
                AlertDialog.Builder myDialog = new AlertDialog.Builder(this);
                myDialog.setTitle("Оформаление заказа");
                myDialog.setMessage("Количество позиций: "+ countPoss + " шт."+"\nОбщая сумма заказа: "+ pricePoss+" грн."+"\n\n Укажите название заказа:");
                EditText etName = new EditText(this);
                myDialog.setView(etName);
                myDialog.setPositiveButton("Отправить", new DialogInterface.OnClickListener() {
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
                            Toast.makeText(AddFood.this, "Введите имя заказа", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                myDialog.setNegativeButton("Отменить", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                myDialog.show();
            }else {
                Toast.makeText(this, "Добавьте позиций", Toast.LENGTH_SHORT).show();
            }

        });
    }
}
