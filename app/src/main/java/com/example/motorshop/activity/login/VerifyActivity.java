package com.example.motorshop.activity.login;

import android.content.Intent;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat;

import com.example.motorshop.activity.R;
import com.example.motorshop.activity.main.MainActivity;


public class VerifyActivity extends AppCompatActivity {

    EditText etOTP;
    ImageView imvDone;
    Button btnSubmit;
    int num;

    AnimatedVectorDrawable avd;
    AnimatedVectorDrawableCompat avdc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setControl();
        setEvent();
    }

    private void setControl() {
        etOTP = (EditText) findViewById(R.id.etOTP);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        imvDone = (ImageView) findViewById(R.id.imvDone);
        imvDone.setVisibility(View.INVISIBLE);
    }

    private void setEvent() {
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                imvDone.setVisibility(v.VISIBLE);
                Drawable drawable = imvDone.getDrawable();
                if(drawable instanceof AnimatedVectorDrawableCompat){
                    avdc = (AnimatedVectorDrawableCompat) drawable;
                    avdc.start();
                    gotoMain();
                }else if(drawable instanceof AnimatedVectorDrawable){
                    avd = (AnimatedVectorDrawable) drawable;
                    avd.start();
                    gotoMain();
                }
            }
        });
    }

    public void gotoMain(){
        if(validOTP() == true){
            startActivity(new Intent(this, MainActivity.class));
        }
    }

    public boolean validOTP(){

        return true;
    }

}