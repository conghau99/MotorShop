package com.example.motorshop.activity.product;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.motorshop.activity.R;
import com.example.motorshop.datasrc.SanPham;

import java.util.ArrayList;


public class ProductActivity extends AppCompatActivity {
    private static final String TAG = "ProductActivity";

    TextView tvType;
    AutoCompleteTextView actvBrand, actvProduct;
    CheckBox chkPriceUp, chkPriceDown;
    Button btnInsertPD;
    GridView gvProduct;

    ArrayList<SanPham> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setControl();
        setEvent();
    }

    private void setControl() {
        tvType = (TextView) findViewById(R.id.tvType);
        actvBrand = (AutoCompleteTextView) findViewById(R.id.actvBrand);
        actvProduct = (AutoCompleteTextView) findViewById(R.id.actvProduct);
        chkPriceUp = (CheckBox) findViewById(R.id.chkPriceUp);
        chkPriceDown = (CheckBox) findViewById(R.id.chkPriceDown);
        btnInsertPD = (Button) findViewById(R.id.btnInsertPD);
        gvProduct = (GridView) findViewById(R.id.gvProduct);
    }

    private void setEvent() {
        ProductAdapter adapter = new ProductAdapter(this, R.layout.item_product, data);
        gvProduct.setAdapter(adapter);

        btnInsertPD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //...
                adapter.notifyDataSetChanged();
            }
        });

        gvProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent = new Intent(getApplicationContext(), ItemActivity.class);
//                intent.putExtra("name", String.valueOf(data.get(position).getName()));
//                startActivity(intent);
                Log.d(TAG, "onItemClick gridview" + data.get(position).getTenSP());
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
        switch (item.getItemId()) {
            case R.id.brands:
                Toast.makeText(ProductActivity.this, "Motors selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.products:
                Toast.makeText(ProductActivity.this, "Accessories selected", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}