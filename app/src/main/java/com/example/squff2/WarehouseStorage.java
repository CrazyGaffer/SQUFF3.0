package com.example.squff2;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.r0adkll.slidr.Slidr;

public class WarehouseStorage extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warehouse_storage);
        Slidr.attach(this);


        Intent intent = getIntent();
        String text = intent.getStringExtra(Scanner.EXTRA_TEXT);

        TextView textView = findViewById(R.id.warehouse_textview);
        textView.setText(text);

    }
}