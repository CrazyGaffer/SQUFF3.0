package com.example.squff2;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import org.w3c.dom.Text;

import java.util.logging.Handler;
import java.util.logging.LogRecord;

import static com.example.squff2.R.color.gray;
import static com.example.squff2.R.color.green;
import static com.example.squff2.R.color.red;

public class ProgressButton {
    private CardView cardView;
    private ConstraintLayout layout;
    private ProgressBar progressBar;
    private TextView textView;
    Animation blink;

    ProgressButton(Context ct, View view){

        blink = AnimationUtils.loadAnimation(ct, R.anim.anim);
        cardView = view.findViewById(R.id.cardView);
        layout = view.findViewById(R.id.ConstLayout);
        progressBar = view.findViewById(R.id.progressBar3);
        textView = view.findViewById(R.id.login);

    }

    void buttonActivated(){
        progressBar.setAnimation(blink);
        progressBar.setVisibility(View.VISIBLE);
        textView.setAnimation(blink);
        textView.setText("PLEASE WAIT");
    }
    void buttonFinished(){
        layout.setBackground(cardView.getResources().getDrawable(green));
        progressBar.setVisibility(View.GONE);
        textView.setText("SUCCESS");

    }

    public void buttonFailure() {
        layout.setBackground(cardView.getResources().getDrawable(red));
        progressBar.setVisibility(View.GONE);
        textView.setText("ERROR");
    }
}