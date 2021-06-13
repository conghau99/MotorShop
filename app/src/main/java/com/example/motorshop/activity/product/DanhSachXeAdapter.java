package com.example.motorshop.activity.product;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.motorshop.activity.R;
import com.example.motorshop.datasrc.Xe;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class DanhSachXeAdapter extends ArrayAdapter {
    Context context;
    int resource;
    public ArrayList<Xe> data;

    public DanhSachXeAdapter(Context context, int resource, ArrayList data) {
        super(context, resource);
        this.context = context;
        this.resource = resource;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Xe getItem(int position)
    {
        return data.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.item_xe, null);


        if (data.size() > 0) {
            TextView tvTenXe = convertView.findViewById(R.id.tvTenXe);
            TextView tvTenHang = convertView.findViewById(R.id.tvTenHang);
            TextView tvMaXe = convertView.findViewById(R.id.tvMaXe);
            TextView tvSoLuong = convertView.findViewById(R.id.tvSoLuong);
            TextView tvDonGia = convertView.findViewById(R.id.tvDonGia);
            TextView tvHanBaoHanh = convertView.findViewById(R.id.tvHanBaoHanh);

            ImageView ivXe = convertView.findViewById(R.id.ivXe);

            Xe xe = data.get(position);

            tvMaXe.setText(xe.getMaSP());
            tvTenXe.setText(xe.getTenSP());

            if (xe.getTenNCC().toString().equals("HD"))
                tvTenHang.setText("Honda");
            if (xe.getTenNCC().toString().equals("YM"))
                tvTenHang.setText("Yamaha");
            if (xe.getTenNCC().toString().equals("SY"))
                tvTenHang.setText("SYM");
            tvSoLuong.setText(Integer.toString(xe.getSoLuong()));
            tvDonGia.setText(Integer.toString(xe.getDonGia()));
            tvHanBaoHanh.setText(xe.getHanBH() + " tháng");


            byte[] hinhAnh = xe.getHinhAnh();
            /*String originalStr = "";

            //chuyen Byte sang String
            for(int i = 0; i < hinhAnh.length; i++)
                originalStr += (char)hinhAnh[i];

            //chuyen String sang Byte
            byte[] b = new byte[originalStr.length()];
            for(int i = 0; i < originalStr.length(); i++) {
                b[i] = (byte)originalStr.charAt(i);
            }*/

            //chuyển byte [] -> bitmap
            Bitmap bitmap = BitmapFactory.decodeByteArray(hinhAnh, 0, hinhAnh.length);
            ivXe.setImageBitmap(bitmap);
        }

        return convertView;
    }
}
