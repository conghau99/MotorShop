package com.example.motorshop.activity.product;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.motorshop.activity.R;
import com.example.motorshop.datasrc.Xe;
import com.example.motorshop.db.DBManager;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import gun0912.tedbottompicker.TedBottomPicker;
import gun0912.tedbottompicker.TedBottomSheetDialogFragment;

public class ThemXeActivity extends AppCompatActivity {
    EditText edtMaXe, edtTenXe, edtSoLuong, edtDonGia, edtHanBaoHanh;
    Spinner spnHangXe;
    ImageView ivPhoTo;
    Button btnChonAnh;

    Xe xe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_xe);

        setControl();
        setEvent();
    }

    private void setEvent() {
        ArrayAdapter adapterHang = ArrayAdapter.createFromResource(this, R.array.Hang, R.layout.item_spinner);
        spnHangXe.setAdapter(adapterHang);

        btnChonAnh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestPermission();
            }
        });
    }

    private void requestPermission() {
        PermissionListener permissionListener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                openImagePicker();
            }

            @Override
            public void onPermissionDenied(List<String> deniedPermissions) {
                Toast.makeText(ThemXeActivity.this, "Permission Denied\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
            }
        };
        TedPermission.with(this)
                .setPermissionListener(permissionListener)
                .setDeniedMessage("If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]")
                .setPermissions(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .check();
    }

    private void openImagePicker() {
        TedBottomPicker.with(ThemXeActivity.this)
                .showGalleryTile(false)
                .showCameraTile(false)
                .show(new TedBottomSheetDialogFragment.OnImageSelectedListener() {
                    @Override
                    public void onImageSelected(Uri uri) {
                        // here is selected image uri
                        try {
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                            ivPhoTo.setImageBitmap(bitmap);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });

        /*TedBottomPicker.OnImageSelectedListener listener = new TedBottomPicker.OnImageSelectedListener() {
            @Override
            public void onImageSelected(Uri uri) {
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                    ivPhoTo.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };*/

    }

    private void setControl() {
        edtMaXe = (EditText) findViewById(R.id.edtMaXe);
        edtSoLuong = (EditText) findViewById(R.id.edtSoLuong);
        edtHanBaoHanh = (EditText) findViewById(R.id.edtHanBaoHanh);
        edtTenXe = (EditText) findViewById(R.id.edtTenXe);
        edtDonGia = (EditText) findViewById(R.id.edtDonGia);
        spnHangXe = (Spinner) findViewById(R.id.spnHangXe);
        btnChonAnh = (Button) findViewById(R.id.btnChonAnh);
        ivPhoTo = (ImageView) findViewById(R.id.ivPhoto);
    }

    private Xe getXe() {
        Xe item = new Xe();
        item.setMaSP(edtMaXe.getText().toString());
        item.setTenSP(edtTenXe.getText().toString());
        item.setSoLuong(Integer.parseInt(edtSoLuong.getText().toString()));
        item.setDonGia(Integer.parseInt(edtDonGia.getText().toString()));
        item.setHanBH(Integer.parseInt(edtHanBaoHanh.getText().toString()));
        if (spnHangXe.getSelectedItem().toString().equals("Honda"))
            item.setTenNCC("HD");
        if (spnHangXe.getSelectedItem().toString().equals("Yamaha"))
            item.setTenNCC("YM");
        if (spnHangXe.getSelectedItem().toString().equals("SYM"))
            item.setTenNCC("SY");

        //chuy???n data imageview -> byte[]
        BitmapDrawable bitmapDrawable = (BitmapDrawable) ivPhoTo.getDrawable();
        Bitmap bitmap = bitmapDrawable.getBitmap();
        bitmap = Bitmap.createScaledBitmap(bitmap, 200, 200, true);
        ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArray);
        byte[] hinhAnh = byteArray.toByteArray();

        item.setHinhAnh(hinhAnh);
        return item;
    }

    private boolean checkNullInfo(EditText e) {
        String s = "" + e.getText();
        if (s.length() == 0) {
            return false;
        } else {
            return true;
        }
    }

    private void thongBao(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    public void themXe(View view) {
        if (!checkNullInfo(edtMaXe)) {
            thongBao("B??? thi???u m?? xe");
            return;
        }

        if (!checkNullInfo(edtTenXe)) {
            thongBao("B??? thi???u t??n xe");
            return;
        }

        if (!checkNullInfo(edtSoLuong)) {
            thongBao("B??? thi???u s??? l?????ng");
            return;
        }

        if (!checkNullInfo(edtDonGia)) {
            thongBao("B??? thi???u ????n gi??");
            return;
        }


        if (!checkNullInfo(edtHanBaoHanh)) {
            thongBao("B??? thi???u h???n b???o h??nh");
            return;
        }

        DBManager database = new DBManager(ThemXeActivity.this);
        Xe xe = getXe();
        database.insertXe(xe);
        Toast.makeText(ThemXeActivity.this, "Th??m Xe Th??nh C??ng", Toast.LENGTH_SHORT).show();
        //finish();
        Intent intent = new Intent(this, QuanLyXeActivity.class);
        startActivity(intent);

        //startActivity(getIntent());
    }

    /*public void chonHanBaoHanh(View view) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                String date = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                edtHanBaoHanh.setText(date);
            }
        }, year, month, day);
        datePickerDialog.show();
    }*/

    public void quayLaiXe(View view) {
        Intent intent = new Intent(ThemXeActivity.this, QuanLyXeActivity.class);
        startActivity(intent);
    }
}