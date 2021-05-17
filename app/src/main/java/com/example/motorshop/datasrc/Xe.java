package com.example.motorshop.datasrc;

import android.graphics.Bitmap;

import java.util.ArrayList;

public class Xe extends SanPham {
    ArrayList<ThongSoXe> danhSachTSX = new ArrayList<>();

    public Xe() { }

    public Xe(String maSP, String tenSP, int soLuong, int donGia, int hanBH, byte[] hinhAnh, String maNCC, ArrayList<ThongSoXe> danhSachTSX) {
        super(maSP, tenSP, soLuong, donGia, hanBH, hinhAnh, maNCC);
        this.danhSachTSX = danhSachTSX;
    }

    public ArrayList<ThongSoXe> getDanhSachTSX() {
        return danhSachTSX;
    }

    public void setDanhSachTSX(ArrayList<ThongSoXe> danhSachTSX) {
        this.danhSachTSX = danhSachTSX;
    }
}
