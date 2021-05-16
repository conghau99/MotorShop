package com.example.motorshop.activity.product;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.motorshop.activity.R;
import com.example.motorshop.datasrc.SanPham;

import java.util.ArrayList;

public class ProductAdapter extends ArrayAdapter<SanPham> {

    Context context;
    int resource;
    ArrayList<SanPham> data;

    public ProductAdapter(Context context, int resource, ArrayList data) {
        super(context, resource, data);
        this.context = context;
        this.resource = resource;
        this.data = data;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(resource, null);

        ImageView imvProduct = convertView.findViewById(R.id.imvProduct);
        TextView tvProductName = convertView.findViewById(R.id.tvProductName);
        TextView tvProductPrice = convertView.findViewById(R.id.tvProductPrice);

        SanPham product = this.data.get(position);
        imvProduct.setImageResource(product.getHinhAnh());
        tvProductName.setText(product.getTenSP());
        tvProductPrice.setText(product.getDonGia());

        return convertView;
    }

}
