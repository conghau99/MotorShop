package com.example.motorshop.activity.product;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.motorshop.activity.R;
import com.example.motorshop.datasrc.ChiTietThongSoXe;
import com.example.motorshop.datasrc.Xe;
import com.example.motorshop.db.DBManager;

import java.util.ArrayList;

public class DanhSachChiTietXeAdapter extends ArrayAdapter {
    Context context;
    int resource;
    public ArrayList<ChiTietThongSoXe> data;
    Xe xe;
    Boolean check = true;

    public DanhSachChiTietXeAdapter(Context context, int resource, ArrayList data) {
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
    public ChiTietThongSoXe getItem(int position) {
        return data.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.item_chi_tiet_xe, null);

        if (data.size() > 0) {

            EditText edtThongSoXe = convertView.findViewById(R.id.edtThongSoXe);
            EditText edtChiTietXe = convertView.findViewById(R.id.edtChiTietXe);

            ChiTietThongSoXe chiTietThongSoXe = data.get(position);

            if (((ChiTietXeActivity) context).tvMaXe.getText().toString().equals(chiTietThongSoXe.getMaXe())  && check == true ) {

                edtChiTietXe.setEnabled(false);

                edtThongSoXe.setEnabled(false);

                if (chiTietThongSoXe.getMaTS() == 1){
                    edtThongSoXe.setText("Trong Luong : ");
                    edtChiTietXe.setText(chiTietThongSoXe.getNoiDungTS());
                }else if (chiTietThongSoXe.getMaTS() == 2){
                    edtThongSoXe.setText("D??i x R???ng x Cao : ");
                    edtChiTietXe.setText(chiTietThongSoXe.getNoiDungTS());
                } else if (chiTietThongSoXe.getMaTS() == 5){
                    edtThongSoXe.setText("Dung t??ch b??nh x??ng : ");
                    edtChiTietXe.setText(chiTietThongSoXe.getNoiDungTS());
                }

                check = false;
            }

            ((ChiTietXeActivity) context).btnSua.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    edtChiTietXe.setEnabled(false);
                    edtThongSoXe.setEnabled(false);
                }
            });


            /*chiTietXeActivity.btnCapNhat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (!checkNullInfo(edtThongSoXe)) {
                        thongBao("B??? thi???u th??ng s??? xe");
                        return;
                    }

                    if (!checkNullInfo(edtChiTietXe)) {
                        thongBao("B??? thi???u chi ti???t xe");
                        return;
                    }


                    DBManager database = new DBManager(chiTietXeActivity);
                    //Xe xe = getXe();
                    ChiTietThongSoXe item = new ChiTietThongSoXe();
                    item.setNoiDungTS(edtChiTietXe.getText().toString());


                    if (edtThongSoXe.getText().toString().equals("Tr???ng l?????ng xe : "))
                        item.setMaTS(1);

                    database.updateCTTSX(chiTietThongSoXe);
                    Toast.makeText(chiTietXeActivity, "C???p Nh???t Th??nh C??ng", Toast.LENGTH_SHORT).show();
                    edtChiTietXe.setEnabled(false);
                    edtThongSoXe.setEnabled(false);
                    Intent intent = new Intent(chiTietXeActivity, ChiTietXeActivity.class);
                    chiTietXeActivity.startActivity(intent);
                }
            });*/

        }

        return convertView;
    }

    /*private boolean checkNullInfo(EditText e) {
        String s = "" + e.getText();
        if (s.length() == 0) {
            return false;
        } else {
            return true;
        }
    }

    private void thongBao(String s) {
        Toast.makeText(((ChiTietXeActivity) context), s, Toast.LENGTH_SHORT).show();
    }*/


}
