package com.example.motorshop.activity.department;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.motorshop.activity.R;
import com.example.motorshop.datasrc.BoPhan;
import com.example.motorshop.helper.Icon_Manager;

import java.util.ArrayList;

public class DepartmentAdapter extends ArrayAdapter<BoPhan> {

    Context context;
    int resource;
    ArrayList<BoPhan> data;

    public DepartmentAdapter(Context context, int resource, ArrayList data) {
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

        TextView tvDPID = convertView.findViewById(R.id.tvDPID);
        TextView tvDPName = convertView.findViewById(R.id.tvDPName);
        TextView tvEditDP = convertView.findViewById(R.id.tvEditDP);
        TextView tvDeleteDP = convertView.findViewById(R.id.tvDeleteDP);

        BoPhan department = this.data.get(position);
        tvDPID.setText(department.getMaBP());
        tvDPName.setText(department.getTenBP());

        Icon_Manager icon_manager = new Icon_Manager();
        tvEditDP.setTypeface(icon_manager.get_icons("fonts/fa-brands-400.ttf",getContext()));
        tvEditDP.setTypeface(icon_manager.get_icons("fonts/fa-regular-400.ttf",getContext()));
        tvEditDP.setTypeface(icon_manager.get_icons("fonts/fa-solid-900.ttf",getContext()));
        tvDeleteDP.setTypeface(icon_manager.get_icons("fonts/fa-brands-400.ttf",getContext()));
        tvDeleteDP.setTypeface(icon_manager.get_icons("fonts/fa-regular-400.ttf",getContext()));
        tvDeleteDP.setTypeface(icon_manager.get_icons("fonts/fa-solid-900.ttf",getContext()));

        tvEditDP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((DepartmentActivity)context).editDepartment(department);
                notifyDataSetChanged();
                ((DepartmentActivity)context).loadDepartment();
                notifyDataSetChanged();
            }
        });

        tvDeleteDP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((DepartmentActivity)context).deleteDepartment(department);
                notifyDataSetChanged();
                ((DepartmentActivity)context).loadDepartment();
                notifyDataSetChanged();
            }
        });

        return convertView;
    }

}
