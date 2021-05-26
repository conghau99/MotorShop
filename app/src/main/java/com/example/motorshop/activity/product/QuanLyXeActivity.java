package com.example.motorshop.activity.product;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import com.example.motorshop.activity.R;
import com.example.motorshop.activity.main.MainActivity;
import com.example.motorshop.activity.product.dialog.*;
import com.example.motorshop.datasrc.Xe;
import com.example.motorshop.db.DBManager;

import java.util.ArrayList;
import java.util.List;

public class QuanLyXeActivity extends AppCompatActivity {
    ListView lvHienThiXe;
    SearchView searchTenXe, searchHang;
    ArrayList<Xe> data = new ArrayList<>();
    DanhSachXeAdapter danhSachXeAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_xe);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setControl();
        setEvent();
        setClick();
    }

    private void setEvent() {
        danhSachXeAdapter = new DanhSachXeAdapter(this, R.layout.item_xe, data);
        lvHienThiXe.setAdapter(danhSachXeAdapter);

        DBManager database = new DBManager(QuanLyXeActivity.this);
        database.loadXe(data);
        danhSachXeAdapter.notifyDataSetChanged();

        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchTenXe.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchTenXe.setSubmitButtonEnabled(true);
        searchTenXe.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchXe(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchXe(newText);
                return false;
            }
        });

        SearchManager searchManager1 = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchHang.setSearchableInfo(searchManager1.getSearchableInfo(getComponentName()));
        searchHang.setSubmitButtonEnabled(true);
        searchHang.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchNCC(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchNCC(newText);
                return false;
            }
        });

    }

    private void setControl() {
        lvHienThiXe = (ListView) findViewById(R.id.lvHienThiXe);
        searchTenXe = (SearchView) findViewById(R.id.searchTenXe);
        searchHang = (SearchView) findViewById(R.id.searchHang);
    }

    private void searchXe(String keyword){
        DBManager dbManager = new DBManager(getApplicationContext());
        ArrayList<Xe> xes = dbManager.searchXe(keyword);
        if (xes != null){
            lvHienThiXe.setAdapter(new DanhSachXeAdapter(getApplicationContext(), R.layout.item_xe, xes));
        }
    }

    private void searchNCC(String keyword){
        DBManager dbManager = new DBManager(getApplicationContext());
        ArrayList<Xe> xes = dbManager.searchNCC(keyword);
        if (xes != null){
            lvHienThiXe.setAdapter(new DanhSachXeAdapter(getApplicationContext(), R.layout.item_xe, xes));
        }
    }

    private void setClick() {

        lvHienThiXe.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                //new LuaChonXe(QuanLyXeActivity.this, (Xe) adapterView.getItemAtPosition(position)).show();
                new LuaChonXe(QuanLyXeActivity.this, danhSachXeAdapter.data.get(position)).show();

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_product, menu);

        return true;
        //return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.motors:
                Intent intent = new Intent(this, QuanLyXeActivity.class);
                startActivity(intent);
                Toast.makeText(QuanLyXeActivity.this, "Xe selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.accessories:
                Intent intent2 = new Intent(this, QuanLyPhuTungActivity.class);
                startActivity(intent2);
                Toast.makeText(QuanLyXeActivity.this, "Phu Tung selected", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void luaChonThem(View view) {
        new LuaChonThemXeDialog(this).show();
    }

    public void chuyenDenManThemXe() {
        Intent i = new Intent(this, ThemXeActivity.class);
        startActivity(i);
    }

    public void chuyenDenManThemPhuTung() {
        Intent intent = new Intent(this, ThemPhuTungActivity.class);
        startActivity(intent);
    }

}