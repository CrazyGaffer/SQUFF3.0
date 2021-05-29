package com.example.squff2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class NewOrder extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_order);

        Button next_page;

        Spinner dropdown = findViewById(R.id.spinner);
        String[] items = new String[]{"Client1", "Client2", "Client3","Client4", "Client5", "Client6" };
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.custom_spinner_dropdown, items);
        dropdown.setAdapter(adapter);


        next_page = findViewById(R.id.button_next);
        next_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewOrder.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}