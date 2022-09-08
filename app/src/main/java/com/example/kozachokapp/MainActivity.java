package com.example.kozachokapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kozachokapp.Adapter.MainAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton floatingActionButton;
    private RecyclerView rcView;
    private MainAdapter adapter;
    private ArrayList<Order> orderArrayList;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private ProgressBar progressBarLoading;
    private TextView textNoOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        database = FirebaseDatabase.getInstance();
        orderArrayList = new ArrayList<>();
        textNoOrder = findViewById(R.id.noOrders);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        findViewById(R.id.btnAddFloat).setOnClickListener(view -> {
            Intent intent = new Intent(this, AddFood.class);
            startActivity(intent);
        });
        progressBarLoading = findViewById(R.id.progressBarLoading);
        rcView = findViewById(R.id.rcMainMenu);
        rcView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MainAdapter();

        adapter.setOrderLists(orderArrayList);
        rcView.setAdapter(adapter);


        getContext();

        new ItemTouchHelper(simpleCallback).attachToRecyclerView(rcView);
        adapter.setOnOrderClickListener(position -> {
            Order order = adapter.orderLists.get(position);
            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
            intent.putExtra("id", order.getOrderName());
            intent.putExtra("list", order.getMenuPos());
            startActivity(intent);
        });



    }

    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            int position = viewHolder.getAdapterPosition();
            String delName = orderArrayList.get(position).getOrderName();
            Order delItems = orderArrayList.get(position);
            myRef = database.getReference("orders");
            myRef.child(delName).removeValue();
            adapter.notifyDataSetChanged();
            Snackbar
                    .make(rcView,"Отменить удаление", Snackbar.LENGTH_LONG)
                    .setAction("Отменить", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            myRef = database.getReference("orders");
                            myRef.child(delName).setValue((delItems));
                            adapter.notifyDataSetChanged();
                        }
                    })
                    .show();
        }
        @Override
        public void onChildDraw (Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive){
            new RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                    .addSwipeLeftBackgroundColor(R.color.red)
                    .addSwipeLeftActionIcon(R.drawable.ic_baseline_delete_24)
                    .addSwipeLeftLabel("Удалить")
                    .setSwipeLeftLabelColor(R.color.white)
                    .create()
                    .decorate();

            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }
    };



    void getContext() {
        progressBarLoading.setVisibility(View.VISIBLE);
        myRef = database.getReference("orders");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                orderArrayList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Order order = new Order();
                    int countOrdres = Integer.parseInt(String.valueOf(dataSnapshot.getChildrenCount()));
                    Log.i("TAG", "" + countOrdres);
                    Log.i("TAG", "" + dataSnapshot.getChildren());
                    order = snapshot.getValue(Order.class);
                    orderArrayList.add(order);
                    if (orderArrayList.size() == 0){
                        textNoOrder.setVisibility(View.VISIBLE);
                    }
                }
                if (orderArrayList.size() == 0){
                    textNoOrder.setVisibility(View.VISIBLE);
                }else {
                    textNoOrder.setVisibility(View.INVISIBLE);
                }
                progressBarLoading.setVisibility(View.INVISIBLE);
                adapter.notifyDataSetChanged();

            }


            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("TAG", "Failed to read value.", error.toException());

            }
        });
    }

//       db.collection("events")
//               .get()
//               .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                   @Override
//                   public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                       if (task.isSuccessful()) {
//                           for (QueryDocumentSnapshot document : task.getResult()) {
//                                OrderList orders = document.toObject(OrderList.class);
//
////                                adapter.setOrderLists(orders);
//
//                               Log.d("TAG", "Данные с БД       "+document.getId() + " => " + document.getData());
//                               Log.d("TAG", document.toString() + " => " + document.getData());
//                               Log.d("TAG", "ORDER  "+ orders);
//                           }
//                       } else {
//                           Log.w("TAG", "Error getting documents.", task.getException());
//                       }
//                   }
//               });
//
//        orders.add(new OrderList(new Order("Суп",new ArrayList(Arrays.asList(new Food("Суп",51,12,1))))));
//        orders.add(new OrderList(new Order("Суп",new ArrayList())));
//        Log.i("TAG", ""+orders);
//
//        String orderName = "Свадьба 01010";
//        ArrayList <Food> order1 = new ArrayList<>();
//        order1.add(new Food("Залупа",15,123,1));
//        order1.add(new Food("egf",15,123,1));
//        order1.add(new Food("Заsadлупа",1534,123,1));
//
//        orders.add(new OrderList(new Order(orderName,order1)));
//
//        Log.i("TAG", ""+orders);
}
