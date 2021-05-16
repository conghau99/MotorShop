package com.example.motorshop.activity.department;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.motorshop.activity.R;
import com.example.motorshop.datasrc.BoPhan;
import com.example.motorshop.db.DBManager;

import java.util.ArrayList;

public class DepartmentActivity extends AppCompatActivity {

    EditText etDPID, etDPName;
    Button btnInsertDP, btnUpdateDP, btnCancelDP;
    ListView lvDepartment;

    BoPhan department;
    ArrayList<BoPhan> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_department);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setControl();
        setEvent();
    }

    private void setControl() {
        etDPID = (EditText) findViewById(R.id.etDPID);
        etDPName = (EditText) findViewById(R.id.etDPName);
        btnInsertDP = (Button) findViewById(R.id.btnInsertDP);
        btnUpdateDP = (Button) findViewById(R.id.btnUpdateDP);
        btnCancelDP = (Button) findViewById(R.id.btnCancelDP);
        lvDepartment = (ListView) findViewById(R.id.lvDepartment);
    }

    private void setEvent() {
        loadDepartment();
        DepartmentAdapter adapter = new DepartmentAdapter(this, R.layout.item_department, data);
        lvDepartment.setAdapter(adapter);

        btnInsertDP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertDepartment();
                adapter.notifyDataSetChanged();
                loadDepartment();
                adapter.notifyDataSetChanged();
            }
        });

        btnUpdateDP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDepartment();
                adapter.notifyDataSetChanged();
                loadDepartment();
                adapter.notifyDataSetChanged();
            }
        });

        btnCancelDP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etDPID.setText("");
                etDPName.setText("");
            }
        });

    }

    public void editDepartment(BoPhan department){
        this.department = department;
        etDPID.setText(department.getMaBP());
        etDPName.setText(department.getTenBP());
    }

    public void insertDepartment(){
        department = new BoPhan();
        department.setMaBP(etDPID.getText().toString().trim());
        department.setTenBP(etDPName.getText().toString().trim());
        new DBManager(DepartmentActivity.this).insertDP(department);
    }

    public void updateDepartment(){
        String departmentID = etDPID.getText().toString().trim();
        String departmentName = etDPName.getText().toString().trim();
        if(!departmentID.equals("") && !departmentName.equals("")){
            department = new BoPhan();
            department.setMaBP(departmentID);
            department.setTenBP(departmentName);
            new DBManager(DepartmentActivity.this).updateDP(department);
        }
    }

    public void deleteDepartment(BoPhan department){
        this.department = department;
        department.setMaBP(etDPID.getText().toString().trim());
        department.setTenBP(etDPName.getText().toString().trim());
        new DBManager(DepartmentActivity.this).deleteDP(department);
    }

    public void loadDepartment(){
        new DBManager(DepartmentActivity.this).loadDPList(data);

    }

}