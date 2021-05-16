package com.example.motorshop.datasrc;

import java.util.ArrayList;

public class PhuTung extends SanPham{
    ArrayList<ThongSoPhuTung> danhSachTSPT= new ArrayList<>();

    public PhuTung() {
        super();
    }

    public PhuTung(String maSP, String tenSP, int soLuong, int donGia, int hanBH, int hinhAnh, String maNCC, ArrayList<ThongSoPhuTung> danhSachTSPT) {
        super(maSP, tenSP, soLuong, donGia, hanBH, hinhAnh, maNCC);
        this.danhSachTSPT = danhSachTSPT;
    }

    public ArrayList<ThongSoPhuTung> getDanhSachTSPT() {
        return danhSachTSPT;
    }

    public void setDanhSachTSPT(ArrayList<ThongSoPhuTung> danhSachTSPT) {
        this.danhSachTSPT = danhSachTSPT;
    }
}