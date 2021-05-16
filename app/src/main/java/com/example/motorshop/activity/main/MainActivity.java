package com.example.motorshop.activity.main;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.motorshop.activity.R;
import com.example.motorshop.activity.brand.BrandActivity;
import com.example.motorshop.activity.product.ProductActivity;
import com.example.motorshop.datasrc.MainItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    ImageView imvMain;
    TextView tvMain;
    GridView gvMain;

    MainItem item;
    ArrayList<MainItem> itemList = new ArrayList<>();

    private static final int NUM_OF_ITEMS = 6;
    private static final int NUM_OF_VALUES = 2;     //per item

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setControl();
        setEvent();
    }

    private void setControl() {
        imvMain = (ImageView) findViewById(R.id.imvMain);
        tvMain = (TextView) findViewById(R.id.tvMain);
        gvMain = (GridView) findViewById(R.id.gvMain);
    }

    private void setEvent() {
        MainAdapter adapter = new MainAdapter(this, R.layout.item_main, itemList);
        gvMain.setAdapter(adapter);

        initItemList();
        adapter.notifyDataSetChanged();

        gvMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

//                for(int i=0; i<itemList.size(); i++){
//                    if(nextLayout.equals(itemList.get(position).getName())){
//                        Intent intent = new Intent(getApplicationContext(), BrandActivity.class);
//                        startActivity(intent);
//                        Log.d(TAG, "onItemClick gridview" + itemList.get(position).getName());
//                    }
//                }

                if(position == 0){
                    Intent intent = new Intent(getApplicationContext(), BrandActivity.class);
                    startActivity(intent);
                    Log.d(TAG, "onItemClick gridview: " + itemList.get(position).getName());
                }
                if(position == 1){
                    Intent intent = new Intent(getApplicationContext(), ProductActivity.class);
                    startActivity(intent);
                    Log.d(TAG, "onItemClick gridview: " + itemList.get(position).getName());
                }
                if(position == 2){
                    Intent intent = new Intent(getApplicationContext(), BrandActivity.class);
                    startActivity(intent);
                    Log.d(TAG, "onItemClick gridview: " + itemList.get(position).getName());
                }
                if(position == 3){
                    Intent intent = new Intent(getApplicationContext(), BrandActivity.class);
                    startActivity(intent);
                    Log.d(TAG, "onItemClick gridview: " + itemList.get(position).getName());
                }
                if(position == 4){
                    Intent intent = new Intent(getApplicationContext(), BrandActivity.class);
                    startActivity(intent);
                    Log.d(TAG, "onItemClick gridview: " + itemList.get(position).getName());
                }
                if(position == 5){
                    Intent intent = new Intent(getApplicationContext(), BrandActivity.class);
                    startActivity(intent);
                    Log.d(TAG, "onItemClick gridview: " + itemList.get(position).getName());
                }

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_options, menu);

        return true;
        //return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.brands:
                Toast.makeText(MainActivity.this, "Brands selected", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), BrandActivity.class);
                startActivity(intent);
                return true;
            case R.id.products:
                Toast.makeText(MainActivity.this, "Products selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.motors:
                Toast.makeText(MainActivity.this, "Motors selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.accessories:
                Toast.makeText(MainActivity.this, "Accessories selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.people:
                Toast.makeText(MainActivity.this, "People selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.customers:
                Toast.makeText(MainActivity.this, "Customers selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.staffs:
                Toast.makeText(MainActivity.this, "Staffs selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.departments:
                Toast.makeText(MainActivity.this, "Departments selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.bills:
                Toast.makeText(MainActivity.this, "Bills selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.statistics:
                Toast.makeText(MainActivity.this, "Statistics selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.statistics_product:
                Toast.makeText(MainActivity.this, "Product Statistics selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.statistics_revenue:
                Toast.makeText(MainActivity.this, "Revenue Statistics selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.guarantees:
                Toast.makeText(MainActivity.this, "Guarantees selected", Toast.LENGTH_SHORT).show();
                return true;
            default: return super.onOptionsItemSelected(item);
        }
    }

    private void initItemList(){
        itemList.add(new MainItem(R.drawable.brand, getResources().getString(R.string.brands)));
        itemList.add(new MainItem(R.drawable.product, getResources().getString(R.string.products)));
        itemList.add(new MainItem(R.drawable.bill, getResources().getString(R.string.bills)));
        itemList.add(new MainItem(R.drawable.guarantee, getResources().getString(R.string.guarantees)));
        itemList.add(new MainItem(R.drawable.statistic, getResources().getString(R.string.statistics)));
        itemList.add(new MainItem(R.drawable.people, getResources().getString(R.string.people)));
    }

}