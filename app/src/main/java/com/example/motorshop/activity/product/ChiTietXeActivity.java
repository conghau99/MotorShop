package com.example.motorshop.activity.product;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.motorshop.activity.R;
import com.example.motorshop.datasrc.ChiTietThongSoXe;
import com.example.motorshop.datasrc.Xe;
import com.example.motorshop.db.DBManager;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class ChiTietXeActivity extends AppCompatActivity {

    DanhSachChiTietXeAdapter danhSachChiTietXeAdapter;
    TextView tvTenSP, tvGia, tvMaXe;
    ImageView ivPhoTo;
    ListView lvHienThiChiTietXe;
    Button btnSua, btnCapNhat, btnThem;
    ArrayList<ChiTietThongSoXe> data = new ArrayList<>();
    String ma;

    Xe xe;
    ChiTietThongSoXe chiTietThongSoXe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_xe);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setControl();
        setEvent();
    }

    private void setEvent() {

        danhSachChiTietXeAdapter = new DanhSachChiTietXeAdapter(this, R.layout.item_chi_tiet_xe, data);
        lvHienThiChiTietXe.setAdapter(danhSachChiTietXeAdapter);

        DBManager database = new DBManager(ChiTietXeActivity.this);
        database.loadCTTSX(data);
        danhSachChiTietXeAdapter.notifyDataSetChanged();

        ChonXe(xe);
    }

    private void setControl() {
        tvTenSP = (TextView) findViewById(R.id.tvTenSP);
        tvMaXe = (TextView) findViewById(R.id.tvMaXe);
        tvGia = (TextView) findViewById(R.id.tvGia);
        ivPhoTo = (ImageView) findViewById(R.id.ivPhoto);
        btnSua = (Button) findViewById(R.id.btnSua);
        btnCapNhat = (Button) findViewById(R.id.btnCapNhat);
        btnThem = (Button) findViewById(R.id.btnThem);
        lvHienThiChiTietXe = (ListView) findViewById(R.id.lvHienThiChiTietXe);
    }

    public void ChonXe(Xe xe) {
        this.xe = xe;
        Intent intent = getIntent();
        String tenXe = intent.getStringExtra("tenXe");
        tvTenSP.setText(tenXe);
        int donGia = intent.getIntExtra("donGia", 0);
        tvGia.setText(Integer.toString(donGia) + "VND");
        String maXe = intent.getStringExtra("maXe");
        tvMaXe.setText(maXe);

        byte[] hinhAnh = intent.getByteArrayExtra("hinhAnh");
        //chuyển byte [] -> bitmap
        Bitmap bitmap = BitmapFactory.decodeByteArray(hinhAnh, 0, hinhAnh.length);
        ivPhoTo.setImageBitmap(bitmap);


    }

}