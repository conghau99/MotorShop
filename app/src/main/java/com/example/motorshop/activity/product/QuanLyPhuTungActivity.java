package com.example.motorshop.activity.product;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import com.example.motorshop.activity.R;
import com.example.motorshop.activity.product.dialog.*;
import com.example.motorshop.datasrc.PhuTung;
import com.example.motorshop.datasrc.Xe;
import com.example.motorshop.db.DBManager;

import java.util.ArrayList;

public class QuanLyPhuTungActivity extends AppCompatActivity {
    ListView lvHienThiPhuTung;
    ArrayList<PhuTung> data = new ArrayList<>();
    SearchView searchTenPT, searchHang;
    DanhSachPhuTungAdapter danhSachPhuTungAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_phu_tung);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setControl();
        setEvent();
        setClick();
    }

    private void setEvent() {
        danhSachPhuTungAdapter = new DanhSachPhuTungAdapter(this, R.layout.item_phu_tung, data);
        lvHienThiPhuTung.setAdapter(danhSachPhuTungAdapter);

        DBManager database = new DBManager(QuanLyPhuTungActivity.this);
        database.loadPT(data);
        danhSachPhuTungAdapter.notifyDataSetChanged();

        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchTenPT.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchTenPT.setSubmitButtonEnabled(true);
        searchTenPT.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchPT(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchPT(newText);
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

    private void searchPT(String keyword){
        DBManager dbManager = new DBManager(getApplicationContext());
        ArrayList<PhuTung> phuTungs = dbManager.searchPT(keyword);
        if (phuTungs != null){
            lvHienThiPhuTung.setAdapter(new DanhSachPhuTungAdapter(getApplicationContext(), R.layout.item_phu_tung, phuTungs));
        }
    }

    private void searchNCC(String keyword){
        DBManager dbManager = new DBManager(getApplicationContext());
        ArrayList<PhuTung> phuTungs = dbManager.searchNCC_PT(keyword);
        if (phuTungs != null){
            lvHienThiPhuTung.setAdapter(new DanhSachPhuTungAdapter(getApplicationContext(), R.layout.item_phu_tung, phuTungs));
        }
    }

    private void setControl() {
        searchTenPT = (SearchView) findViewById(R.id.searchTenPT);
        searchHang = (SearchView) findViewById(R.id.searchHang);
        lvHienThiPhuTung = (ListView) findViewById(R.id.lvHienThiPhuTung);
    }

    private void setClick() {

        lvHienThiPhuTung.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                //new LuaChonXe(QuanLyXeActivity.this, (Xe) adapterView.getItemAtPosition(position)).show();
                new LuaChonPhuTung(QuanLyPhuTungActivity.this, danhSachPhuTungAdapter.data.get(position)).show();

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
                Toast.makeText(QuanLyPhuTungActivity.this, "Xe selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.accessories:
                Intent intent2 = new Intent(this, QuanLyPhuTungActivity.class);
                startActivity(intent2);
                Toast.makeText(QuanLyPhuTungActivity.this, "Phu Tung selected", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void luaChonThem(View view) {
        new LuaChonThemPhuTungDialog(this).show();
    }

    public void chuyenDenManThemXe() {
        Intent i = new Intent(this, ThemXeActivity.class);
        startActivity(i);
    }

    public void chuyenDenManThemPhuTung() {
        Intent i = new Intent(this, ThemPhuTungActivity.class);
        startActivity(i);
    }

}