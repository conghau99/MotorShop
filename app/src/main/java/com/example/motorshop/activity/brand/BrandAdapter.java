package com.example.motorshop.activity.brand;

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
import com.example.motorshop.datasrc.NhaCungCap;
import com.example.motorshop.helper.Icon_Manager;

import java.util.ArrayList;

public class BrandAdapter extends ArrayAdapter<NhaCungCap> {

    Context context;
    int resource;
    ArrayList<NhaCungCap> data;

    public BrandAdapter(Context context, int resource, ArrayList data) {
        super(context, resource, data);
        this.context = context;
        this.resource = resource;
        this.data = data;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(resource, null);

        ImageView imvBR = convertView.findViewById(R.id.imvBR);
        TextView tvBRName = convertView.findViewById(R.id.tvBRName);
        TextView tvEditBR = convertView.findViewById(R.id.tvEditBR);
        TextView tvDeleteBR = convertView.findViewById(R.id.tvDeleteBR);

        NhaCungCap brand = this.data.get(position);
        imvBR.setImageResource(brand.getLogo());
        tvBRName.setText(brand.getTenNCC());

        Icon_Manager icon_manager = new Icon_Manager();
        tvEditBR.setTypeface(icon_manager.get_icons("fonts/fa-brands-400.ttf",getContext()));
        tvEditBR.setTypeface(icon_manager.get_icons("fonts/fa-regular-400.ttf",getContext()));
        tvEditBR.setTypeface(icon_manager.get_icons("fonts/fa-solid-900.ttf",getContext()));
        tvDeleteBR.setTypeface(icon_manager.get_icons("fonts/fa-brands-400.ttf",getContext()));
        tvDeleteBR.setTypeface(icon_manager.get_icons("fonts/fa-regular-400.ttf",getContext()));
        tvDeleteBR.setTypeface(icon_manager.get_icons("fonts/fa-solid-900.ttf",getContext()));

        tvEditBR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((BrandActivity)context).editBrand(brand);
                notifyDataSetChanged();
                ((BrandActivity)context).loadBrand();
                notifyDataSetChanged();
            }
        });

        tvDeleteBR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((BrandActivity)context).deleteBrand(brand);
                notifyDataSetChanged();
                ((BrandActivity)context).loadBrand();
                notifyDataSetChanged();
            }
        });

        return convertView;
    }

}
