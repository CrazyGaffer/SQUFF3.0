package com.example.squff2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.google.zxing.Result;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import com.r0adkll.slidr.Slidr;

import es.dmoral.toasty.Toasty;

import static com.example.squff2.R.color.green;

public class Scanner extends AppCompatActivity {

    public static final String EXTRA_TEXT = "com.example.squff2.EXTRA_TEXT";

    CodeScanner codeScanner;
    CodeScannerView codeScannerView;
    TextView scan_result, check;
    Animation blink;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner);

        codeScannerView = findViewById(R.id.scanner_view);
        scan_result = findViewById(R.id.Scanning_result);
        codeScanner = new CodeScanner(this, codeScannerView);
        check = findViewById(R.id.addToWarehouse);
        progressBar = findViewById(R.id.progress_bar_scanner);
        Slidr.attach(this);
        blink = AnimationUtils.loadAnimation(this, R.anim.anim);


        codeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull Result result) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        scan_result.setText(result.getText());
                    }
                });
            }
        });

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                check.setVisibility(View.GONE);
                scan_result.setAnimation(blink);

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        progressBar.setVisibility(View.GONE);
                        check.setVisibility(View.VISIBLE);
                        check.setText("");
                        Handler handler1 = new Handler();
                        handler1.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                check.setBackground(getDrawable(R.drawable.ic_check));
                                Intent intent = new Intent(Scanner.this, WarehouseStorage.class);
                                String text = scan_result.getText().toString();
                                intent.putExtra(EXTRA_TEXT, text);
                                startActivity(intent);
                            }
                        },3500);
                    }
                }, 2500);
            }
        });

    }

    @Override
    protected void onResume(){
        super.onResume();
        requestForCamera();

    }

    private void requestForCamera() {
        Dexter.withActivity(this).withPermission(Manifest.permission.CAMERA).withListener(new PermissionListener() {
            @Override
            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                codeScanner.startPreview();
            }

            @Override
            public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
                Toasty.error(Scanner.this, "Camera Permission is Required!", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                permissionToken.continuePermissionRequest();
            }
        }).check();

    }



    }
