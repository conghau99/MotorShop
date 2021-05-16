package com.example.motorshop.activity.main;

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
import com.example.motorshop.datasrc.MainItem;

import java.util.ArrayList;

public class MainAdapter extends ArrayAdapter<MainItem> {

    Context context;
    int resource;
    ArrayList<MainItem> data;

    public MainAdapter(Context context, int resource, ArrayList data) {
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

        ImageView imvMain = convertView.findViewById(R.id.imvMain);
        TextView tvMain = convertView.findViewById(R.id.tvMain);

        MainItem mainItems = this.data.get(position);
        imvMain.setImageResource(mainItems.getImageMain());
        tvMain.setText(mainItems.getName());

        return convertView;
    }

}
