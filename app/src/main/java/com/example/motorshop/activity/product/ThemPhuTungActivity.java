package com.example.motorshop.activity.product;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
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
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.motorshop.activity.R;
import com.example.motorshop.datasrc.PhuTung;
import com.example.motorshop.db.DBManager;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import gun0912.tedbottompicker.TedBottomPicker;
import gun0912.tedbottompicker.TedBottomSheetDialogFragment;

public class ThemPhuTungActivity extends AppCompatActivity {
    EditText edtMaPhuTung, edtTenPhuTung, edtSoLuong, edtDonGia, edtHanBaoHanh;
    Spinner spnHangPhuTung;
    ImageView ivPhoTo;
    Button btnChonAnh;

    PhuTung phuTung;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_phu_tung);

        setControl();
        setEvent();
    }

    private void setEvent() {
        ArrayAdapter adapterHang = ArrayAdapter.createFromResource(this, R.array.HangPT, R.layout.item_spinner);
        spnHangPhuTung.setAdapter(adapterHang);

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
                Toast.makeText(ThemPhuTungActivity.this, "Permission Granted", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPermissionDenied(List<String> deniedPermissions) {
                Toast.makeText(ThemPhuTungActivity.this, "Permission Denied\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
            }
        };
        TedPermission.with(this)
                .setPermissionListener(permissionListener)
                .setDeniedMessage("If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]")
                .setPermissions(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .check();
    }

    private void openImagePicker() {
        TedBottomPicker.with(ThemPhuTungActivity.this)
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
    }

    private void setControl() {
        edtMaPhuTung = (EditText) findViewById(R.id.edtMaPhuTung);
        edtSoLuong = (EditText) findViewById(R.id.edtSoLuong);
        edtHanBaoHanh = (EditText) findViewById(R.id.edtHanBaoHanh);
        edtTenPhuTung = (EditText) findViewById(R.id.edtTenPhuTung);
        edtDonGia = (EditText) findViewById(R.id.edtDonGia);
        spnHangPhuTung = (Spinner) findViewById(R.id.spnHangPhuTung);
        btnChonAnh = (Button) findViewById(R.id.btnChonAnh);
        ivPhoTo = (ImageView) findViewById(R.id.ivPhoto);
    }

    private PhuTung getPhuTung() {
        PhuTung item = new PhuTung();
        item.setMaSP(edtMaPhuTung.getText().toString());
        item.setTenSP(edtTenPhuTung.getText().toString());
        item.setSoLuong(Integer.parseInt(edtSoLuong.getText().toString()));
        item.setDonGia(Integer.parseInt(edtDonGia.getText().toString()));
        item.setHanBH(Integer.parseInt(edtHanBaoHanh.getText().toString()));
        if (spnHangPhuTung.getSelectedItem().toString().equals("Ohlins"))
            item.setTenNCC("OH");
        if (spnHangPhuTung.getSelectedItem().toString().equals("Akrapovic"))
            item.setTenNCC("AK");

        //chuyển data imageview -> byte[]
        BitmapDrawable bitmapDrawable = (BitmapDrawable) ivPhoTo.getDrawable();
        Bitmap bitmap = bitmapDrawable.getBitmap();
        ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArray);
        byte[] hinhAnh = byteArray.toByteArray();

        item.setHinhAnh(hinhAnh);
        return item;
    }

    public void ChonPhuTung(PhuTung phuTung) {
        this.phuTung = phuTung;
        edtMaPhuTung.setText(phuTung.getMaSP());
        edtTenPhuTung.setText(phuTung.getTenSP());
        edtSoLuong.setText(phuTung.getSoLuong());
        edtDonGia.setText(phuTung.getDonGia());
        edtHanBaoHanh.setText(phuTung.getHanBH());
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

    public void themPhuTung(View view) {
        if (!checkNullInfo(edtMaPhuTung)) {
            thongBao("Bị thiếu mã phụ tùng");
            return;
        }

        if (!checkNullInfo(edtTenPhuTung)) {
            thongBao("Bị thiếu tên phụ tùng");
            return;
        }

        if (!checkNullInfo(edtSoLuong)) {
            thongBao("Bị thiếu số lượng");
            return;
        }

        if (!checkNullInfo(edtDonGia)) {
            thongBao("Bị thiếu đơn giá");
            return;
        }


        if (!checkNullInfo(edtHanBaoHanh)) {
            thongBao("Bị thiếu hạn bảo hành");
            return;
        }

        DBManager database = new DBManager(ThemPhuTungActivity.this);
        PhuTung phuTung = getPhuTung();
        database.insertPT(phuTung);
        Toast.makeText(ThemPhuTungActivity.this, "Thêm Phụ Tùng Thành Công", Toast.LENGTH_SHORT).show();
        //finish();
        Intent intent = new Intent(this, QuanLyPhuTungActivity.class);
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

    public void quayLaiPhuTung(View view) {
        Intent intent = new Intent(ThemPhuTungActivity.this, QuanLyPhuTungActivity.class);
        startActivity(intent);
    }

}