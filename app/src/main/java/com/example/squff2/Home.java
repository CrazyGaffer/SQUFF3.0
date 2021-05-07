package com.example.squff2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {

    RecyclerView recyclerView;
    Adapter adapter;
    ArrayList<String> items;
    ArrayList<Integer> images;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); // for hiding title
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home);


        items = new ArrayList<>();
        items.add("Receiving");
        items.add("Shipping");
        items.add("Transfer");
        items.add("Warehouse Storage");
        items.add("Contacts");
        items.add("Settings");

        images = new ArrayList<>();
        images.add(R.drawable.ic_receive);
        images.add(R.drawable.ic_shipping);
        images.add(R.drawable.ic_transfer);
        images.add(R.drawable.ic_storage);
        images.add(R.drawable.ic_baseline_phone_24);
        images.add(R.drawable.ic_settings);


        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Adapter(this,items,images);
        recyclerView.setAdapter(adapter);



    }
}


