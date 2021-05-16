package com.example.motorshop.activity.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.motorshop.activity.R;
import com.example.motorshop.activity.main.MainActivity;
import com.example.motorshop.db.DBManager;

public class LoginActivity extends AppCompatActivity {

    EditText etUsn, etPwd;
    Button btnLogin;
    TextView tvGuest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().hide();
        setControl();
        setEvent();
    }

    private void setControl() {
        etUsn = (EditText) findViewById(R.id.etUsn);
        etPwd = (EditText) findViewById(R.id.etPwd);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        tvGuest = (TextView) findViewById(R.id.tvGuest);
    }

    private void setEvent() {

        btnLogin.setOnClickListener(v -> login());

        tvGuest.setOnClickListener(v -> gotoProducts());

    }

    public void setFullScreen() {
        this.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        );
    }

    public void login() {
//        if (validLogin() == true) {
            gotoVerify();
//        }
    }

    public void gotoProducts() {
        startActivity(new Intent(this, MainActivity.class));
    }

    public void gotoVerify() {
        startActivity(new Intent(this, VerifyActivity.class));
    }

    public boolean validLogin() {
        boolean check = false;
        if (etUsn.getText().toString().trim().length() < 4 || etPwd.getText().toString().trim().length() < 4) {
            Toast.makeText(getApplicationContext(), "Username & Password at least 4 chars!", Toast.LENGTH_SHORT).show();
            check = false;
        }
        if (etUsn.getText().toString().trim().length() >= 4 && etPwd.getText().toString().trim().length() >= 4) {
            DBManager db = new DBManager(LoginActivity.this);
//            if (db.ifIDExist() {
                Toast.makeText(getApplicationContext(), "Login Succeeded", Toast.LENGTH_SHORT).show();
                check = true;
//            } else {
//                Toast.makeText(getApplicationContext(), "Login Suspended", Toast.LENGTH_SHORT).show();
//                check = false;
//            }
        }
        return check;
    }

}