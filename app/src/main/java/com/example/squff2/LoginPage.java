package com.example.squff2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import es.dmoral.toasty.Toasty;

public class LoginPage extends AppCompatActivity {
    EditText login_email, login_pass;
    View view;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login_page);



            login_pass = findViewById(R.id.login_password);
            login_email = findViewById(R.id.login_email);
            view = findViewById(R.id.include);
            mAuth = FirebaseAuth.getInstance();


//     If user already logged in before, move straight to HomePage

        if (isNetworkAvailable()) {
            if (mAuth.getCurrentUser() != null) {
                startActivity(new Intent(LoginPage.this, Home.class));
                finish();
            }

        }


        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = login_email.getText().toString().trim();
                String password = login_pass.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    login_email.setError("Email is required!");
                }

                if(TextUtils.isEmpty(password)){
                    login_pass.setError("Password is required!");
                    return;
                }


//                Accessing Database

                mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            ProgressButton progressButton = new ProgressButton(LoginPage.this, view);
                            progressButton.buttonActivated();
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    progressButton.buttonFinished();
                                    Handler handler1 = new Handler();
                                    handler1.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            Intent intent = new Intent(LoginPage.this, Home.class);
                                            startActivity(intent);

                                        }
                                    },3000);

                                }
                            },3000);
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull  Exception e) {
                        ProgressButton progressButton = new ProgressButton(LoginPage.this, view);
                        progressButton.buttonActivated();
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                progressButton.buttonFailure();
                            }
                        },3000);

                    }
                });

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







