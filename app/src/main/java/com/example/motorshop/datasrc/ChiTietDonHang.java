package com.example.motorshop.datasrc;

import java.util.ArrayList;

public class ChiTietDonHang extends DonHang{
    private ArrayList<ChiTietSanPhamDonHang> danhSachSPDH = new ArrayList<>();

    public ChiTietDonHang() { }

    public ChiTietDonHang(String maDH, String ngayDat, String cmnd, String tenNV, ArrayList<ChiTietSanPhamDonHang> danhSachSPDH) {
        super(maDH, ngayDat, cmnd, tenNV);
        this.danhSachSPDH = danhSachSPDH;
    }

    public ArrayList<ChiTietSanPhamDonHang> getDanhSachSPDH() {
        return danhSachSPDH;
    }

    public void setDanhSachSPDH(ArrayList<ChiTietSanPhamDonHang> danhSachSPDH) {
        this.danhSachSPDH = danhSachSPDH;
    }
}
