package com.example.squff2;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.single.PermissionListener;
import com.r0adkll.slidr.Slidr;

import java.security.acl.NotOwnerException;

import es.dmoral.toasty.Toasty;

public class Contacts extends AppCompatActivity {

    private Button button, buttonToWM, buttonToSD;
    private TextView email, emailToWM, emailToSD;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        button = findViewById(R.id.buttonCall);
        buttonToSD = findViewById(R.id.buttonCallToSD);
        buttonToWM = findViewById(R.id.buttonCallToWM);
        email = findViewById(R.id.email);
        emailToSD = findViewById(R.id.emailToSD);
        emailToWM = findViewById(R.id.emailToWM);
        Slidr.attach(this);


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_CEO:
                        Toast.makeText(Contacts.this, "CEO contacts", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_managers:
                        Toast.makeText(Contacts.this, "Managers contacts", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Contacts.this, Managers_Contacts.class);
                        startActivity(intent);
                        notify();
                        break;
                    case R.id.action_nearby:
                        Toast.makeText(Contacts.this, "Warehouse Workers", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_drivers:
                        Toast.makeText(Contacts.this, "Drivers", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });




        buttonToWM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:58171667"));

                if (ActivityCompat.checkSelfPermission(Contacts.this,
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                startActivity(callIntent);
            }
        });



        buttonToSD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:51234567"));

                if (ActivityCompat.checkSelfPermission(Contacts.this,
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                startActivity(callIntent);
            }
        });




        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:56624537"));

                if (ActivityCompat.checkSelfPermission(Contacts.this,
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                startActivity(callIntent);
            }
        });

        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            sendEmail();
            }
        });


        emailToWM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail1();
            }
        });


        emailToSD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail2();
            }
        });



    }

    private void sendEmail2() {
        String[] TO = {"stefano.fiorenza@gmail.com"};
        String[] CC = {""};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Your subject");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Email message goes here");

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
        } catch (android.content.ActivityNotFoundException ex) {
            Toasty.info(Contacts.this, "There is no email client installed.", Toasty.LENGTH_SHORT).show();
        }
    }

    private void sendEmail1() {
        String[] TO = {"ivan.lastovko1234@gmail.com"};
        String[] CC = {""};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Your subject");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Email message goes here");

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
        } catch (android.content.ActivityNotFoundException ex) {
            Toasty.info(Contacts.this, "There is no email client installed.", Toasty.LENGTH_SHORT).show();
        }
    }

    private void sendEmail() {
        String[] TO = {"krusman99@gmail.com"};
        String[] CC = {""};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Your subject");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Email message goes here");

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
        } catch (android.content.ActivityNotFoundException ex) {
            Toasty.info(Contacts.this, "There is no email client installed.", Toasty.LENGTH_SHORT).show();
        }
    }


}