package com.example.motorshop.activity.product.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.example.motorshop.activity.R;
import com.example.motorshop.activity.product.QuanLyXeActivity;
import com.example.motorshop.activity.product.SuaXeActivity;
import com.example.motorshop.datasrc.Xe;
import com.example.motorshop.db.DBManager;

import java.util.ArrayList;

public class LuaChonXe extends Dialog {

    Xe x;
    QuanLyXeActivity quanLyXeActivity;
    SuaXeActivity suaXeActivity;
    ArrayList<Xe> data = new ArrayList<>();

    public LuaChonXe(Context context, Xe xe) {
        super(context);
        this.x = xe;
        this.quanLyXeActivity = (QuanLyXeActivity) context;

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.dg_nua_chon_xe);

        //ánh xạ
        TextView tvTenXe = findViewById(R.id.tvTenXe);
        TextView tvSuaThongTinXe = findViewById(R.id.tvSuaThongTinXe);
        TextView tvXoaXe = findViewById(R.id.tvXoaXe);

        //set thông tin
        tvTenXe.setText(x.getTenSP());
        tvSuaThongTinXe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(quanLyXeActivity, SuaXeActivity.class);
                intent.putExtra("maXe",x.getMaSP().trim());
                intent.putExtra("tenXe",x.getTenSP().trim());
                intent.putExtra("soLuong",x.getSoLuong());
                intent.putExtra("donGia",x.getDonGia());
                intent.putExtra("hanBH",x.getHanBH());
                intent.putExtra("hinhAnh",x.getHinhAnh());
                quanLyXeActivity.startActivity(intent);
                dismiss();
            }
        });

        tvXoaXe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBManager database = new DBManager(quanLyXeActivity);
                database.deleteXe(x);
                database.loadXe(data);
                Toast.makeText(quanLyXeActivity, "Xóa Xe Thành Công", Toast.LENGTH_SHORT).show();
                dismiss();
                quanLyXeActivity.startActivity(new Intent(quanLyXeActivity, QuanLyXeActivity.class));
            }
        });
    }
}
