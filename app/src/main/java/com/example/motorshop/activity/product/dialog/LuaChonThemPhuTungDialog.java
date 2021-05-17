package com.example.motorshop.activity.product.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.motorshop.activity.R;
import com.example.motorshop.activity.product.QuanLyPhuTungActivity;

public class LuaChonThemPhuTungDialog extends Dialog {

    QuanLyPhuTungActivity quanLyPhuTungActivity;

    public LuaChonThemPhuTungDialog(@NonNull Context context) {
        super(context);
        this.quanLyPhuTungActivity = (QuanLyPhuTungActivity) context;

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dg_nua_chon_them);

        TextView tvThemPhuTung = findViewById(R.id.tvThemPhuTung);
        TextView tvThemXe = findViewById(R.id.tvThemXe);

        tvThemXe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quanLyPhuTungActivity.chuyenDenManThemXe();
                dismiss();
            }
        });

        tvThemPhuTung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quanLyPhuTungActivity.chuyenDenManThemPhuTung();
                dismiss();
            }
        });

    }
}
