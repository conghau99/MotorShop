package com.example.motorshop.activity.brand;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.motorshop.activity.R;
import com.example.motorshop.datasrc.NhaCungCap;
import com.example.motorshop.db.DBManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class BrandActivity extends AppCompatActivity {

    FloatingActionButton btnAddBR;
    ListView lvBrand;

    NhaCungCap brand;
    ArrayList<NhaCungCap> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setControl();
        setEvent();
    }

    private void setControl() {
        lvBrand = (ListView) findViewById(R.id.lvBrand);
        btnAddBR = (FloatingActionButton) findViewById(R.id.btnAddBR);
    }

    private void setEvent() {
//        loadBrand();
        BrandAdapter adapter = new BrandAdapter(this, R.layout.item_brand, data);
        lvBrand.setAdapter(adapter);
        data.add(new NhaCungCap("YM", "Yamaha", "", "", "", R.drawable.bill));
        data.add(new NhaCungCap("HD", "Honda", "", "", "", R.drawable.brand));
        data.add(new NhaCungCap("HD", "Honda", "", "", "", R.drawable.brand));
        data.add(new NhaCungCap("YM", "Yamaha", "", "", "", R.drawable.bill));
        data.add(new NhaCungCap("HD", "Honda", "", "", "", R.drawable.brand));
        data.add(new NhaCungCap("HD", "Honda", "", "", "", R.drawable.brand));
        data.add(new NhaCungCap("HD", "Honda", "", "", "", R.drawable.brand));
        data.add(new NhaCungCap("YM", "Yamaha", "", "", "", R.drawable.bill));
        data.add(new NhaCungCap("HD", "Honda", "", "", "", R.drawable.brand));
        data.add(new NhaCungCap("YM", "Yamaha", "", "", "", R.drawable.bill));
        data.add(new NhaCungCap("HD", "Honda", "", "", "", R.drawable.brand));
        data.add(new NhaCungCap("YM", "Yamaha", "", "", "", R.drawable.bill));
        data.add(new NhaCungCap("HD", "Honda", "", "", "", R.drawable.brand));
        data.add(new NhaCungCap("YM", "Yamaha", "", "", "", R.drawable.bill));


//        btnInsertBR.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                insertBrand();
                adapter.notifyDataSetChanged();
//                loadBrand();
//                adapter.notifyDataSetChanged();
//                resetText();
//            }
//        });

//        btnUpdateBR.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                updateBrand();
//                adapter.notifyDataSetChanged();
//                loadBrand();
//                adapter.notifyDataSetChanged();
//            }
//        });

//        btnCancelBR.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                resetText();
//            }
//        });

        btnAddBR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Choose button", Toast.LENGTH_SHORT).show();
            }
        });

        lvBrand.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "Long Click: pos " + position, Toast.LENGTH_SHORT).show();

                AlertDialog.Builder builder = new AlertDialog.Builder(BrandActivity.this);

                builder.setTitle("Confirm");
                builder.setMessage("Are you sure?");

                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing but close the dialog
                        Toast.makeText(getApplicationContext(), "Choose YES Dialog: pos " + position, Toast.LENGTH_SHORT).show();

                        dialog.dismiss();
                    }
                });

                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        // Do nothing
                        dialog.dismiss();
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();

                return true;

            }
        });
    }

    public void editBrand(NhaCungCap brand){
//        this.brand = brand;
//        imvBR.(brand.getMaNCC());
//        etBRName.setText(brand.getTenNCC());
    }
//
//    public void insertBrand(){
//        brand = new NhaCungCap();
//        brand.setMaNCC(etBRID.getText().toString().trim());
//        brand.setTenNCC(etBRName.getText().toString().trim());
//        new DBManager(BrandActivity.this).insertBR(brand);
//    }
//
//    public void updateBrand(){
//        String departmentID = etBRID.getText().toString().trim();
//        String departmentName = etBRName.getText().toString().trim();
//        if(!departmentID.equals("") && !departmentName.equals("")){
//            brand = new NhaCungCap();
//            brand.setMaNCC(departmentID);
//            brand.setTenNCC(departmentName);
//            new DBManager(BrandActivity.this).updateBR(brand);
//        }
//    }
//
    public void deleteBrand(NhaCungCap brand){
//        this.brand = brand;
//        brand.setMaNCC(etBRID.getText().toString().trim());
//        brand.setTenNCC(etBRName.getText().toString().trim());
        new DBManager(BrandActivity.this).deleteBR(brand.getTenNCC());
    }
//
    public void loadBrand(){
        new DBManager(BrandActivity.this).loadBRList(data);
    }
//
//    public void resetText(){
//        etBRID.setText("");
//        etBRName.setText("");
//    }












}