package com.example.motorshop.activity.login;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.motorshop.activity.*;
import com.example.motorshop.activity.R;
import com.example.motorshop.activity.main.MainActivity;
import com.example.motorshop.activity.product.DanhSachXeAdapter;
import com.example.motorshop.activity.product.QuanLyXeActivity;
import com.example.motorshop.datasrc.PhuTung;
import com.example.motorshop.datasrc.Xe;
import com.example.motorshop.db.DBManager;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    QuanLyXeActivity quanLyXeActivity;
    EditText etUsn, etPwd;
    Button btnLogin;
    TextView tvGuest;
    ArrayList<Xe> data = new ArrayList<>();

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

        DBManager db = new DBManager(LoginActivity.this);
        db.getWritableDatabase();
        db.initData();
        initDataXe();
        btnLogin.setOnClickListener(v -> login());

        tvGuest.setOnClickListener(v -> gotoProducts());

    }

    public void initDataXe(){
        DBManager database = new DBManager(LoginActivity.this);
        database.getWritableDatabase();
        Xe xe = new Xe("HD01", "Wave Alpha", 8, 18099000, 24, imgToByteArray(R.drawable.hd_wavea), "HD");
        database.insertXe(xe);
        xe = new Xe("HD02", "Winner X", 9, 45999000, 48, imgToByteArray(R.drawable.hd_winx), "HD");
        database.insertXe(xe);
        xe = new Xe("HD03", "Vision", 10, 29999000, 36, imgToByteArray(R.drawable.hd_vision), "HD");
        database.insertXe(xe);
        xe = new Xe("YM01", "Sirius", 8, 21099000, 36, imgToByteArray(R.drawable.ym_sirius), "YM");
        database.insertXe(xe);
        xe = new Xe("YM02", "Exciter", 9, 50499000, 48, imgToByteArray(R.drawable.ym_exciter), "YM");
        database.insertXe(xe);
        xe = new Xe("YM03", "Grande", 10, 49999000, 48, imgToByteArray(R.drawable.ym_grande), "YM");
        database.insertXe(xe);
        PhuTung phuTung = new PhuTung("OH01", "Nhan dan trang tri Ohlins 1", 5, 403000, 3, imgToByteArray(R.drawable.oh_sticker1) , "OH");
        database.insertPT(phuTung);
        phuTung = new PhuTung("OH02", "Phuoc Ohlins Vario", 5, 8500000, 24, imgToByteArray(R.drawable.oh_phuoc_vario), "OH");
        database.insertPT(phuTung);
        phuTung = new PhuTung("AK01", "Nhan dan Akrapovic chong nhiet nhom", 5, 212000, 12, imgToByteArray(R.drawable.ak_sticker1), "AK");
        database.insertPT(phuTung);
        phuTung = new PhuTung("AK02", "Po Akrapovic GP Titan Yamaha R3", 5, 8000000, 24, imgToByteArray(R.drawable.ak_gp_titan_r3), "AK");
        database.insertPT(phuTung);
        database.loadXe(data);
    }

    public byte[] imgToByteArray( final int i ) {
        // get image from drawable
        Bitmap image = BitmapFactory.decodeResource(getResources(), i);
        Bitmap bmpimg = Bitmap.createScaledBitmap(image, 200, 200, true);
        // convert bitmap to byte
        ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
        bmpimg.compress(Bitmap.CompressFormat.PNG, 100, byteArray);
        byte[] hinhAnh = byteArray.toByteArray();
        return hinhAnh;
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