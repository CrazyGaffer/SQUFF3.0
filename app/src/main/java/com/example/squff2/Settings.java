package com.example.squff2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.r0adkll.slidr.Slidr;

import org.w3c.dom.Text;

import es.dmoral.toasty.Toasty;

import static com.example.squff2.LoginPage.MyPREFERENCES;

public class Settings extends AppCompatActivity {

    ImageView logout_btn;
    Button changePass;
    TextView username;
    FirebaseAuth mAuth;
    SharedPreferences sharedpreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        username = findViewById(R.id.username);

        mAuth = FirebaseAuth.getInstance();


        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        String email = sharedpreferences.getString("Username", null);
        username.setText(email);





        logout_btn = findViewById(R.id.logout_button);
        changePass = findViewById(R.id.changePass);
        mAuth = FirebaseAuth.getInstance();
        Slidr.attach(this);
        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isNetworkAvailable()) {
                    mAuth.signOut();
                    Intent intent = new Intent(Settings.this, LoginPage.class);
                    startActivity(intent);
                }
            }
        });


        changePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



    }

    private boolean isNetworkAvailable() {
            ConnectivityManager connectivityManager
                    = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.isConnected();
        }

    }
