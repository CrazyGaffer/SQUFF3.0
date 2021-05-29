package com.example.squff2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;


import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{
    private ArrayList<ExampleItem> mExampleList;
    private RecyclerView mRecyclerView;
    private ExampleAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        createExampleList();
        buildRecyclerView();


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation1);
        bottomNavigationView.setSelectedItemId(R.id.AllOrders);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.OpenedOrders:
                        startActivity(new Intent(getApplicationContext(),OpenedOrders.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.AllOrders:
                        return true;

                    case R.id.ClosedOrders:
                        startActivity(new Intent(getApplicationContext(),ClosedOrders.class));
                        overridePendingTransition(0,0);
                        return true;

                }
                return false;
            }
        });


    }

    public void removeItem(int position) {
        mExampleList.remove(position);
        mAdapter.notifyItemRemoved(position);
    }
    public void changeItem(int position, String text) {
        mExampleList.get(position).changeText1(text);
        mAdapter.notifyItemChanged(position);
    }
    public void createExampleList() {
        mExampleList = new ArrayList<>();
        mExampleList.add(new ExampleItem(R.drawable.ic_android, "Product", "Driver", "Truck", "Plate Number"));
        mExampleList.add(new ExampleItem(R.drawable.ic_android, "Product", "Driver", "Truck", "Plate Number"));
        mExampleList.add(new ExampleItem(R.drawable.ic_android, "Product", "Driver", "Truck", "Plate Number"));
        mExampleList.add(new ExampleItem(R.drawable.ic_android, "Product", "Driver", "Truck", "Plate Number"));
        mExampleList.add(new ExampleItem(R.drawable.ic_android, "Product", "Driver", "Truck", "Plate Number"));
        mExampleList.add(new ExampleItem(R.drawable.ic_android, "Product", "Driver", "Truck", "Plate Number"));
        mExampleList.add(new ExampleItem(R.drawable.ic_android, "Product", "Driver", "Truck", "Plate Number"));
        mExampleList.add(new ExampleItem(R.drawable.ic_android, "Product", "Driver", "Truck", "Plate Number"));
        mExampleList.add(new ExampleItem(R.drawable.ic_android, "Product", "Driver", "Truck", "Plate Number"));
        mExampleList.add(new ExampleItem(R.drawable.ic_android, "Product", "Driver", "Truck", "Plate Number"));
        mExampleList.add(new ExampleItem(R.drawable.ic_android, "Product", "Driver", "Truck", "Plate Number"));



    }


    public void buildRecyclerView() {
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new ExampleAdapter(mExampleList);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new ExampleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                changeItem(position, "Clicked");
            }
            @Override
            public void onDeleteClick(int position) {
                removeItem(position);
            }
        });
    }

    }
